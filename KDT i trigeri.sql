--DISTINKT TIP

CREATE OR REPLACE TYPE PIB AS OBJECT(
   brojPIBa NUMBER(9),
   MEMBER FUNCTION get_brojPIBa RETURN NUMBER,
   CONSTRUCTOR FUNCTION PIB(brojPIBa NUMBER) RETURN SELF AS RESULT
)
INSTANTIABLE NOT FINAL;
/

CREATE OR REPLACE TYPE BODY PIB AS 
   MEMBER FUNCTION get_brojPIBa RETURN NUMBER IS
   BEGIN
      RETURN SELF.brojPIBa;
   END; 

   CONSTRUCTOR FUNCTION PIB(brojPIBa NUMBER) RETURN SELF AS RESULT AS
   BEGIN
      IF brojPIBa >= 100000000 AND brojPIBa < 1000000000 THEN 
         SELF.brojPIBa := brojPIBa;
      ELSE 
         RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'PIB mora imati 9 cifara!');
      END IF;
      RETURN;
   END;
END;
/

CREATE TABLE Mesto(
SifraMesta NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(20));

INSERT INTO Mesto VALUES (1,'Beograd');
INSERT INTO Mesto VALUES (2,'Leskovac');
SELECT * FROM Mesto;

--Skladiste je u optimizaciji napravljeno kao view
/*CREATE TABLE Skladiste(
SifraSkladista  NUMBER(10) PRIMARY KEY,
PIB PIB,
NazivFirme VARCHAR2(30),
Naziv VARCHAR2(30),
Adresa VARCHAR2(30),
SifraMesta NUMBER(10) NOT NULL,
CONSTRAINT mesto_fk FOREIGN KEY (SifraMesta) REFERENCES Mesto(SifraMesta));*/

INSERT INTO Skladiste VALUES (1, PIB(123456789), 'Stark', 'Hala 1', 'Prote Stojana 92', 1);

SELECT s.SifraSkladista AS ID, s.Naziv, s.PIB.get_brojPIBa() AS PIB
FROM Skladiste s;

UPDATE Skladiste s SET s.PIB.brojPIBa = 123456788 WHERE s.SifraSkladista = 1;

DELETE Skladiste s WHERE s.PIB.get_brojPIBa() = 123456788;


--STRUKTUIRANI TIP

CREATE OR REPLACE TYPE Naziv AS OBJECT(
   ime VARCHAR2(20),
   tip VARCHAR2(10),
   MEMBER FUNCTION get_ime RETURN VARCHAR2,
   MEMBER FUNCTION get_tip RETURN VARCHAR2,
   CONSTRUCTOR FUNCTION Naziv(ime VARCHAR2, tip VARCHAR2) RETURN SELF AS RESULT
)
INSTANTIABLE NOT FINAL;
/

CREATE OR REPLACE TYPE BODY Naziv AS
   MEMBER FUNCTION get_ime RETURN VARCHAR2 IS
   BEGIN
      RETURN SELF.ime;
   END;
   MEMBER FUNCTION get_tip RETURN VARCHAR2 IS
   BEGIN
      RETURN SELF.tip;
   END;

CONSTRUCTOR FUNCTION Naziv(ime VARCHAR2, tip VARCHAR2) RETURN SELF AS RESULT AS
   BEGIN
      SELF.ime := ime;
      IF tip NOT IN ('doo', 'ad', 'od', 'kd') THEN 
            RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Tip preduzeca moze biti samo neka od ponudjenih vrednosti: doo, ad, od, kd!');
      ELSE 
            SELF.tip := tip;
      END IF;
      RETURN;
   END;
END;
/

CREATE TABLE Dobavljac(
SifraDobavljaca  NUMBER(10) PRIMARY KEY,
PIB PIB,
Naziv Naziv,
Adresa VARCHAR2(30),
SifraMesta NUMBER(10) NOT NULL,
CONSTRAINT mesto1_fk FOREIGN KEY (SifraMesta) REFERENCES Mesto(SifraMesta));

INSERT INTO Dobavljac VALUES (1, PIB(123456888), Naziv('Lenovo', 'ad'),  'Presevska 2', 1);

SELECT d.naziv.get_ime() AS Ime, d.naziv.get_tip() AS TipPreduzeca 
FROM Dobavljac d;

UPDATE Dobavljac d SET d.naziv.ime = 'Lenovo1' WHERE d.SifraDobavljaca = 1;

DELETE Dobavljac d WHERE d.naziv.get_ime() = 'Lenovo1';


---TRIGERI


CREATE TABLE Klijent(
SifraKlijenta NUMBER(10) PRIMARY KEY,
ImePrezime VARCHAR2(40),
Kontakt VARCHAR2(30) );

INSERT INTO Klijent VALUES (1,'Jovan Ilic', '+38165 4857235');
INSERT INTO Klijent VALUES (2,'Ana Tosic', '+38164 2011087');
SELECT * FROM Klijent;

CREATE TABLE Radnik(
SifraRadnika NUMBER(10) PRIMARY KEY,
ImePrezime VARCHAR2(40) );

INSERT INTO Radnik VALUES (1,'Teodora Stojanovic');
INSERT INTO Radnik VALUES (2,'Ilija Markovic');
INSERT INTO Radnik VALUES (3,'Jovan Markovic');
SELECT * FROM Radnik;

CREATE TABLE Marka(
SifraMarke NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30) );

INSERT INTO Marka VALUES (1,'Lenovo');
INSERT INTO Marka VALUES (2,'HP');
INSERT INTO Marka VALUES (3,'Dell');
INSERT INTO Marka VALUES (4,'Acer');
INSERT INTO Marka VALUES (5,'Apple');
SELECT * FROM Marka;

CREATE TABLE Uredjaj(
SifraUredjaja NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30),
Tip VARCHAR2(30),
SifraMarke NUMBER(10) NOT NULL,
CONSTRAINT marka_fk FOREIGN KEY (SifraMarke) REFERENCES Marka(SifraMarke));

INSERT INTO Uredjaj VALUES (1,'Iphone 13', 'Telefon', 5);
INSERT INTO Uredjaj VALUES (2,'Ideapad 3', 'Laptop', 3);
INSERT INTO Uredjaj VALUES (3,'Ideapad 3', 'Laptop', 1);
SELECT * FROM Uredjaj;

CREATE TABLE Radnja(
SifraRadnje NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30),
Adresa VARCHAR2(30),
SifraMesta NUMBER(10) NOT NULL,
CONSTRAINT mesto2_fk FOREIGN KEY (SifraMesta) REFERENCES Mesto(SifraMesta));

INSERT INTO Radnja VALUES (1,'Stark', 'Tosin bunar 43', 1);
SELECT * FROM Radnja;

CREATE TABLE RadniNalog(
BrojNaloga NUMBER(10) PRIMARY KEY,
DatumIzdavanja DATE,
DatumIzvrsenja DATE,
DatumPrometa DATE,
OpisGreske VARCHAR2(50),
Napomena VARCHAR2(100),
DodatneInformacije VARCHAR2(30),
KomentarIzvrsioca VARCHAR2(30),
SifraKlijenta NUMBER(10) NOT NULL,
SifraRadnika NUMBER(10) NOT NULL,
SifraUredjaja NUMBER(10) NOT NULL,
SifraRadnje NUMBER(10) NOT NULL,
SifraMarke NUMBER(10) ,
CONSTRAINT klijent_fk FOREIGN KEY (SifraKlijenta) REFERENCES Klijent(SifraKlijenta),
CONSTRAINT radnik_fk FOREIGN KEY (SifraRadnika) REFERENCES Radnik(SifraRadnika),
CONSTRAINT uredjaj_fk FOREIGN KEY (SifraUredjaja) REFERENCES Uredjaj(SifraUredjaja),
CONSTRAINT radnja_fk FOREIGN KEY (SifraRadnje) REFERENCES Radnja(SifraRadnje),
CONSTRAINT marka1_fk FOREIGN KEY (SifraMarke) REFERENCES Marka(SifraMarke));

--

CREATE TABLE JedinicaMere(
SifraJediniceMere NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30) );

INSERT INTO JedinicaMere VALUES (1,'komad');
INSERT INTO JedinicaMere VALUES (2,'metar');
INSERT INTO JedinicaMere VALUES (3,'h');
SELECT * FROM JedinicaMere;

CREATE TABLE VrstaDobra(
SifraDobra NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30),
SifraJediniceMere NUMBER(10) NOT NULL,
CONSTRAINT JM_fk FOREIGN KEY (SifraJediniceMere) REFERENCES JedinicaMere(SifraJediniceMere));

INSERT INTO VrstaDobra VALUES (1,'Kabal', 2);
INSERT INTO VrstaDobra VALUES (2,'Baterija 30W', 1);
INSERT INTO VrstaDobra VALUES (3,'Usluga servisa', 3);
SELECT * FROM VrstaDobra;

CREATE TABLE Cena(
SifraDobra NUMBER(10) NOT NULL,
DatumOd DATE NOT NULL,
Iznos DECIMAL(10,2),
CONSTRAINT nalog_pk PRIMARY KEY (SifraDobra, DatumOd));


CREATE TABLE Usluga(
SifraDobra NUMBER(10) PRIMARY KEY,
CONSTRAINT SD1_fk FOREIGN KEY (SifraDobra) REFERENCES VrstaDobra(SifraDobra));

INSERT INTO Usluga VALUES (3);
SELECT * FROM Usluga u JOIN VrstaDobra vd ON (u.sifradobra=vd.sifradobra);

CREATE TABLE RezervniDeo(
SifraDobra NUMBER(10) PRIMARY KEY,
CONSTRAINT SD_fk FOREIGN KEY (SifraDobra) REFERENCES VrstaDobra(SifraDobra));

INSERT INTO RezervniDeo VALUES (1);
INSERT INTO RezervniDeo VALUES (2);
SELECT * FROM RezervniDeo rd JOIN VrstaDobra vd ON (rd.sifradobra=vd.sifradobra);


CREATE TABLE StavkaRadnogNaloga(
BrojNaloga NUMBER(10) NOT NULL,
RB NUMBER(10) NOT NULL,
Kolicina NUMBER(10),
PDVStopa NUMBER(10),
OpisGreske VARCHAR2(50),
SifraDobra NUMBER(10) NOT NULL,
CONSTRAINT dobro_fk FOREIGN KEY (SifraDobra) REFERENCES VrstaDobra(SifraDobra),
CONSTRAINT nalog1_pk PRIMARY KEY (BrojNaloga, RB));


--3NF

--INSERT RadniNalog, upisuje u SifraMarke automatdki, u odnosu na SifraUredjaja

CREATE OR REPLACE TRIGGER INS_RADNINALOG
BEFORE INSERT ON RadniNalog
FOR EACH ROW
DECLARE v_marka NUMBER(10);
BEGIN
  SELECT SifraMarke INTO v_marka FROM Uredjaj WHERE SifraUredjaja = :NEW.SifraUredjaja;
  :NEW.SifraMarke := v_marka;
END;
/ 

INSERT INTO RadniNalog VALUES (3,'09-JAN-22','09-JAN-22', '09-JAN-22','Laptop ne moze da se upali','','','',1,1,3,1,null,null);
SELECT * FROM RadniNalog;

--UPDATE SifraUredjaja u Radnom nalogu, unosi se nova SifraMarke u odnosu na SifruUredjaja

CREATE OR REPLACE TRIGGER UPD_SIFRAUREDJAJA
BEFORE UPDATE OF SifraUredjaja ON RadniNalog
FOR EACH ROW
DECLARE v_marka1 NUMBER(10);
BEGIN
  SELECT SifraMarke INTO v_marka1 FROM Uredjaj WHERE SifraUredjaja = :NEW.SifraUredjaja;
  :NEW.SifraMarke := v_marka1;
END;
/

UPDATE RadniNalog SET SifraUredjaja = 2 WHERE BrojNaloga=1;

--UPDATE SifraMarke u Nalogu, ne moze direktno da se menja

CREATE OR REPLACE TRIGGER UPD_SIFRAMARKE_ZABRANA
BEFORE UPDATE OF SifraMarke ON RadniNalog
FOR EACH ROW 
BEGIN
  RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Nije moguca direktno menjati SifruMarke!');
END;
/

UPDATE RadniNalog SET SifraMarke = 1 WHERE BrojNaloga=1;

--UPDATE SifraMarke u Uredjaju, da ukoliko Uredjaj koji se menja ima radne naloge i kod njih se menja SifraMarke

CREATE OR REPLACE TRIGGER UPD_MARKAUREDJAJ
AFTER UPDATE OF SifraMarke ON Uredjaj
FOR EACH ROW
DECLARE PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
  EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_SIFRAMARKE_ZABRANA DISABLE';
  UPDATE RadniNalog SET SifraMarke = :NEW.SifraMarke WHERE SifraUredjaja = :NEW.SifraUredjaja;
BEGIN
  EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_SIFRAMARKE_ZABRANA ENABLE';
END;
END;
/

UPDATE Uredjaj SET SifraMarke = 2 WHERE SifraUredjaja=2;
SELECT * FROM RadniNalog;


--2NF

--INSERT Stavka, u koloni OpisGreske upisuje ono sto pise u Radnom nalogu

CREATE OR REPLACE TRIGGER INS_STAVKA_NALOGA
BEFORE INSERT ON StavkaRadnogNaloga
FOR EACH ROW
DECLARE v_opis VARCHAR2(50);
BEGIN
  SELECT OpisGreske INTO v_opis FROM RadniNalog WHERE BrojNaloga =: NEW.BrojNaloga;
  :NEW.OpisGreske := v_opis;
END;
/

INSERT INTO StavkaRadnogNaloga VALUES (1, 1, 1, 10, null, 3);
SELECT * FROM StavkaRadnogNaloga;

--UPDATE OpisGreske u Stavci, zabrana

CREATE OR REPLACE TRIGGER UPD_OPIS_ZABRANA
BEFORE UPDATE OF OpisGreske ON StavkaRadnogNaloga
FOR EACH ROW 
BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Nije moguce direktno menjati Opis greke u stavci!');
END;
/

UPDATE StavkaRadnogNaloga SET OpisGreske = 'Nesto novo' WHERE Rb = 1 AND BrojNaloga=1;

--UPDATE BrojNaloga u stavci, zabrana

CREATE OR REPLACE TRIGGER UPD_BROJNALOGA_ZABRANA
BEFORE UPDATE OF BrojNaloga ON StavkaRadnogNaloga
FOR EACH ROW 
BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Nije moguce menjati BrojNaloga u stavci!');
END;
/

UPDATE StavkaRadnogNaloga SET BrojNaloga=1 WHERE Rb = 1 AND BrojNaloga=1;

--UPDATE OpisGreske u Nalogu, automatski se prenosi na stavke tog naloga

CREATE OR REPLACE TRIGGER UPD_OPIS_GRESKE
AFTER UPDATE OF OpisGreske ON RadniNalog
FOR EACH ROW
DECLARE PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
  EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_OPIS_ZABRANA DISABLE';
  UPDATE StavkaRadnogNaloga SET OpisGreske = :NEW.OpisGreske WHERE BrojNaloga = :NEW.BrojNaloga;
BEGIN
  EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_OPIS_ZABRANA ENABLE';
END;
END;
/

UPDATE RadniNalog  SET OpisGreske = 'Novi opis!' WHERE BrojNaloga = 1;
SELECT * FROM RadniNalog;
SELECT * FROM StavkaRadnogNaloga;


