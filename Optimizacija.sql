--Logicka optimizacijija, tehnike

--REPEATING SINGLE DETAIL WITH MASTER

ALTER TABLE VrstaDobra 
ADD AktuelnaCena DECIMAL(10,2);

CREATE OR REPLACE NONEDITIONABLE PACKAGE VrstaDobraPaket AS 
SifraDobra NUMBER(10);
END VrstaDobraPaket;
/

select * from vrstadobra;
select * from vrstadobra vd join cena c on (vd.sifradobra=c.sifradobra);

--Triger koji odredjuje sifru dobra i cuva u globalnu promenljivu
CREATE OR REPLACE TRIGGER VRSTADOBRA_CENA
BEFORE INSERT OR UPDATE OR DELETE ON Cena
FOR EACH ROW
BEGIN
  IF (INSERTING OR UPDATING)
  THEN
    BEGIN VrstaDobraPaket.SifraDobra := :NEW.SifraDobra; END;
  ELSE
    BEGIN VrstaDobraPaket.SifraDobra := :OLD.SifraDobra; END;
  END IF;
END;
/

--Triger koji sprecava direktnu izmenu AktuelneCene u tabeli VrstaDobra
CREATE OR REPLACE TRIGGER UPD_AKTUELNA_CENA_ZABRANA
BEFORE UPDATE OF AktuelnaCena ON VrstaDobra
FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(NUM => -20000,MSG => 'Ne mozete direktno menjati vrednost AktuelnaCena!');
END;
/

UPDATE VrstaDobra SET AktuelnaCena=520.00 WHERE SifraDobra=2;

--Procedura koja racuna aktuelnu cenu robe
CREATE OR REPLACE PROCEDURE AKTUELNA_CENA_DOBRA (SDobra IN NUMBER) AS
Aktuelna VrstaDobra.AktuelnaCena%type :=0;
BEGIN
  BEGIN
    SELECT Iznos INTO Aktuelna 
    FROM Cena
    WHERE SifraDobra = SDobra AND DatumOd=(SELECT MAX(DatumOd) FROM Cena WHERE SifraDobra = SDobra AND DatumOd <= SYSDATE);
    EXCEPTION WHEN no_data_found THEN Aktuelna := null;
  END;
  UPDATE VrstaDobra
  SET AktuelnaCena = Aktuelna
  WHERE SifraDobra = SDobra;
END;
/

--Procedure koje iskljucuju i ukljucuju triger koji vrsi zabranu izmene aktuelne cene, da bi se promena prenela, ukoliko se izmena izvrsi u tabeli Cena
CREATE OR REPLACE PROCEDURE ISKLJUCI_TRIGER_CENA AS                                                                           
PRAGMA AUTONOMOUS_TRANSACTION;                                                                                                                
BEGIN                                                                                                                                         
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_AKTUELNA_CENA_ZABRANA DISABLE';                                               
END ISKLJUCI_TRIGER_CENA;
/

CREATE OR REPLACE PROCEDURE UKLJUCI_TRIGER_CENA AS                                                                           
PRAGMA AUTONOMOUS_TRANSACTION;                                                                                                                
BEGIN                                                                                                                                         
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_AKTUELNA_CENA_ZABRANA ENABLE';                                               
END UKLJUCI_TRIGER_CENA;
/

--Triger koji poziva proceduru koja azurira aktuelnu cenu
CREATE OR REPLACE TRIGGER ROBA_CENA2
AFTER INSERT OR UPDATE OR DELETE ON Cena
DECLARE
  SDobra NUMBER(10) := VrstaDobraPaket.SifraDobra;
BEGIN
  ISKLJUCI_TRIGER_CENA();
  AKTUELNA_CENA_DOBRA(SDobra);
  UKLJUCI_TRIGER_CENA();
END;
/

SELECT * FROM VrstaDobra;
SELECT * FROM Cena;
INSERT INTO Cena VALUES (3,'25-JAN-23', 600.50);
INSERT INTO Cena VALUES (1,'26-JAN-23', 150.67);
UPDATE Cena SET Iznos=550.00 WHERE SifraDobra=2;
DELETE FROM Cena WHERE SifraDobra=3 ;




--STORING DERIVABLE VALUES

ALTER TABLE RadniNalog 
ADD UkupanIznos DECIMAL(10,2);

CREATE OR REPLACE NONEDITIONABLE PACKAGE StavkaPaket AS 
BrojNaloga NUMBER(10);
END StavkaPaket;
/

--Triger koji cuva BrojNaloga u globalnu promenljivu
CREATE OR REPLACE TRIGGER UKUPAN_IZNOS_NALOG
BEFORE INSERT OR UPDATE OR DELETE ON StavkaRadnogNaloga 
FOR EACH ROW
BEGIN
  IF (INSERTING OR UPDATING)
  THEN
    BEGIN StavkaPaket.BrojNaloga := :NEW.BrojNaloga; END;
  ELSE
    BEGIN StavkaPaket.BrojNaloga := :OLD.BrojNaloga; END;
  END IF;
END;
/

--Triger koji sprecava direktnu izmenu Ukupnogiznosa u tabeli RadniNalog
CREATE OR REPLACE TRIGGER UPD_UKUPNO_ZABRANA
BEFORE UPDATE OF UkupanIznos ON RadniNalog
FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(NUM => -20000,MSG => 'Ne mozete direktno menjati vrednost UkupanIznos!');
END;
/

UPDATE RadniNalog SET UkupanIznos=520.00 WHERE BrojNaloga=1;

--Procedura koja racuna ukupan iznos radnog naloga
CREATE OR REPLACE PROCEDURE UKUPAN_IZNOS_SUM (BrNal IN NUMBER) AS
Ukupno VrstaDobra.AktuelnaCena%type :=0;
BEGIN
    SELECT SUM(vd.AktuelnaCena*s.Kolicina+vd.AktuelnaCena*s.PDVStopa*0.01) INTO Ukupno 
    FROM StavkaRadnogNaloga s JOIN VrstaDobra vd ON (s.SifraDobra=vd.SifraDobra)
    WHERE s.BrojNaloga = BrNal;
    UPDATE RadniNalog
    SET UkupanIznos = Ukupno
    WHERE BrojNaloga = BrNal;
END;
/

--Procedure koje iskljucuju i ukljucuju triger koji vrsi zabranu izmene ukupne cene, da bi se promena prenela, ukoliko se izmena izvrsi u tabeli Stavke, tj ako se promeni kolicina
CREATE OR REPLACE PROCEDURE ISKLJUCI_TRIGER_UKUPNO AS                                                                           
PRAGMA AUTONOMOUS_TRANSACTION;                                                                                                                
BEGIN                                                                                                                                         
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_UKUPNO_ZABRANA DISABLE';                                               
END ISKLJUCI_TRIGER_UKUPNO;
/

CREATE OR REPLACE PROCEDURE UKLJUCI_TRIGER_UKUPNO AS                                                                           
PRAGMA AUTONOMOUS_TRANSACTION;                                                                                                                
BEGIN                                                                                                                                         
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_UKUPNO_ZABRANA ENABLE';                                               
END UKLJUCI_TRIGER_UKUPNO;
/

--Triger koji poziva proceduru koja azurira ukupanIznos
CREATE OR REPLACE TRIGGER UKUPAN_IZNOS_NALOG2
AFTER INSERT OR UPDATE OR DELETE ON StavkaRadnogNaloga
DECLARE
  BrojNaloga NUMBER(10) := StavkaPaket.BrojNaloga;
BEGIN
  ISKLJUCI_TRIGER_UKUPNO();
  UKUPAN_IZNOS_SUM(BrojNaloga);
  UKLJUCI_TRIGER_UKUPNO();
END;
/

SELECT * FROM RadniNalog;
SELECT * FROM StavkaRadnogNaloga;
INSERT INTO StavkaRadnogNaloga VALUES (3, 4, 2, 10, null, 1);
UPDATE StavkaRadnogNaloga SET Kolicina=3 WHERE BrojNaloga=1 AND RB=2;





--FIZICKA OPTIMIZACIJA

--INDEKSI

SELECT Naziv FROM Mesto WHERE Naziv='Beograd';

CREATE INDEX NazivMesta_IND ON Mesto(Naziv);


SELECT * FROM RadniNalog WHERE SifraRadnje=1;

CREATE INDEX Radnja_IND ON RadniNalog(SifraRadnje);


--VERTIKALNO PARTICIONISANJE

/*CREATE TABLE Skladiste(
SifraSkladista  NUMBER(10) PRIMARY KEY,
PIB PIB,
NazivFirme VARCHAR2(30),
Naziv VARCHAR2(30),
Adresa VARCHAR2(30),
SifraMesta NUMBER(10) NOT NULL,
CONSTRAINT mesto_fk FOREIGN KEY (SifraMesta) REFERENCES Mesto(SifraMesta));*/



CREATE TABLE SkladisteOsnovno(
SifraSkladista  NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30),
Adresa VARCHAR2(30),
SifraMesta NUMBER(10) NOT NULL,
CONSTRAINT mesto5_fk FOREIGN KEY (SifraMesta) REFERENCES Mesto(SifraMesta));

CREATE TABLE SkladisteDetalji(
SifraSkladista  NUMBER(10) PRIMARY KEY,
PIB PIB,
NazivFirme VARCHAR2(30));


--Kreiranje pogleda
CREATE OR REPLACE VIEW Skladiste AS 
SELECT so.SifraSkladista, so.Naziv, so.Adresa, so.SifraMesta, sd. PIB, sd.NazivFirme
FROM SkladisteOsnovno so, SkladisteDetalji sd 
WHERE so.SifraSkladista=sd.SifraSkladista;

--Kreiranje INSTEAD OF trigera za sve naredbe

CREATE OR REPLACE TRIGGER INSTEAD_INS_SKLADISTE
INSTEAD OF INSERT ON Skladiste
FOR EACH ROW
BEGIN
  INSERT INTO SkladisteOsnovno(SifraSkladista, Naziv, Adresa, SifraMesta)
  VALUES (:NEW.SifraSkladista, :NEW.Naziv, :NEW.Adresa, :NEW.SifraMesta);
  INSERT INTO SkladisteDetalji(SifraSkladista, PIB, NazivFirme)
  VALUES (:NEW.SifraSkladista, :NEW.PIB, :NEW.NazivFirme);
END;
/

CREATE OR REPLACE TRIGGER INSTEAD_UPD_SKLADISTE
INSTEAD OF UPDATE ON Skladiste
FOR EACH ROW
BEGIN
  UPDATE SkladisteOsnovno 
  SET Naziv = :NEW.Naziv, Adresa = :NEW.Adresa,  SifraMesta = :NEW.SifraMesta
  WHERE SifraSkladista = :NEW.SifraSkladista;
  UPDATE SkladisteDetalji 
  SET PIB = :NEW.PIB, NazivFirme = :NEW.NazivFirme
  WHERE SifraSkladista = :NEW.SifraSkladista;
END;
/

CREATE OR REPLACE TRIGGER INSTEAD_DEL_SKLADISTE
INSTEAD OF DELETE ON Skladiste
FOR EACH ROW
BEGIN
  DELETE FROM SkladisteOsnovno WHERE SifraSkladista = :OLD.SifraSkladista;
  DELETE FROM SkladisteDetalji WHERE SifraSkladista = :OLD.SifraSkladista;
END;
/

INSERT INTO Skladiste VALUES (2,'Hala 2','Prote Stojana 92', 1, PIB(123456789), 'Stark');

SELECT s.SifraSkladista, s.Naziv, s.PIB.get_brojPIBa() AS PIB
FROM Skladiste s;

select * from skladiste;
select * from skladisteosnovno;


UPDATE Skladiste s SET s.PIB.brojPIBa = 123456788 WHERE s.SifraSkladista = 1;

DELETE Skladiste s WHERE s.PIB.get_brojPIBa() = 123456788;

SELECT * FROM SkladisteOsnovno;

--HORIZONTALNO PARTICIONISANJE


ALTER TABLE RadniNalog
MODIFY PARTITION BY RANGE (DatumIzdavanja) (
  PARTITION pruzene_usluge_2022 VALUES LESS THAN (TO_DATE('01/01/2023', 'MM/DD/YYYY')),
  PARTITION pruzene_usluge_2023 VALUES LESS THAN (TO_DATE('01/01/2024', 'MM/DD/YYYY'))
);

SELECT * FROM RadniNalog PARTITION(pruzene_usluge_2022);





