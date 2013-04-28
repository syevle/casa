	
Create Table Starcast
(
Starcast_name Varchar2(30)   --Unique Name of All Starcast (Hero\Heroie\Vilian etc)
);

Alter Table Starcast
  add constraint PK_STAR primary key (Starcast_name);
  
  
Create Table Director
(
Director_name Varchar2(30)   --Unique Name of All Director (Director for movie and assistant director)
);

Alter Table Director
  add constraint PK_DIR primary key (Director_name);
  
 
Create Table Creator
(
Creator_name Varchar2(30)   --Unique Name of All Creator (Director for TV serial and assistant director i.e director is called Creator for tv serial) 
);

Alter Table Creator
  add constraint PK_CRET Primary key (Creator_name);
  

Create Table Movie_Details
(
  Media_format  		VARCHAR2(3),            --DVD/BLR  (dvd or bluray) 
  LANGUAGES     		VARCHAR2(20),			--language for example Hindi, English
  Mov_code      		VARCHAR2(10) not null,  --For example D2890, B5890, DH660, D3894A sequence generated. unique identifier.
  Mov_name      		VARCHAR2(50) not null,  --Name of Movie
  Rel_date      		DATE,					--Movie Release Date
  Starcast_name_1   	VARCHAR2(30),			--Starcast name 1 from starcast table and unique for movie name. (selection check option to select starcast)
  Starcast_name_2  		VARCHAR2(30),			--same as above  starcast name 2
  Starcast_name_3 		VARCHAR2(30),			--same as above  starcast name 3
  Starcast_name_4  		VARCHAR2(30),			--same as above  starcast name 4
  Starcast_name_5   	VARCHAR2(30),			--same as above  starcast name 5
  Starcast_name_6  		VARCHAR2(30),			--same as above  starcast name 6
  DIRECTOR_1    		VARCHAR2(30),			--director name 1 of movie from director table and unique for mov name (selection check option to select director)
  DIRECTOR_2   			VARCHAR2(30),			--same as above  director name 2
  DIRECTOR_3  			VARCHAR2(30),			--same as above  director name 3
  CREATOR_1     		VARCHAR2(30),			--Creator name 1 of tv serial from creator table and unique for tv serial (selection check option to select creator)
  CREATOR_2    			VARCHAR2(30),			--same as above creator name 2
  CREATOR_3   			VARCHAR2(30),			--same as above creator name 3
  GENRE_1       		VARCHAR2(20),			--Type of Movie (selection from list box)  for example ACTION, CRIMAL, COMEDY
  GENRE_2      			VARCHAR2(20),			--Type of Movie (selection from list box)
  GENRE_3     			VARCHAR2(20),			--Type of Movie (selection from list box)
  GENRE_EXTRA   		VARCHAR2(20),			--Type of Movie (selection from list box)  
  COUNTRY       		VARCHAR2(20),			--which country movie is origin for axample India, US
  TAG_LINE      		VARCHAR2(500),			--Movie short story
  DVD_LOCATION  		VARCHAR2(20),			--DVD location. where in Library dvd is kept (for example act\comdy)
  AVAL_STATUS   		CHAR(1) 	 not null,  --DVD available for rent (for example Y\N)    
  MOV_PATH      		VARCHAR2(255),			--DVD Image location path
  ENTRY_DT   			DATE,					--When record was added\modify in table
  UPLOD_REQ   			CHAR(1)					--If change made in dvd then require to upload in website.
);
  
Alter Table Movie_Details
  add constraint PK_MOVCD Primary key (Mov_code);  
  
  
create sequence TRAN_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 22
increment by 1
Cache 20;
  