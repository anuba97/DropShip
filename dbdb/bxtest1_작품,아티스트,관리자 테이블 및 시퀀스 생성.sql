-- SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables; -- ������̺� ������Ű�� ������ �����Ե�

--������ ���� �ȸ��� / insert�� ���� work_seq.nextval �̰� �ȳ־.
--- 1. work, artist, admin ���̺� �� seq�� ���� -----
--------------- Work��ǰ ���̺�, work_seq ����
CREATE TABLE Work
(
    id                   NUMBER(4)         NOT NULL, 
    work_name            VARCHAR2(200)     NOT NULL, 
    artist_id            NUMBER(4)         NOT NULL, 
    work_genre_name      VARCHAR2(50)      DEFAULT '�ʻ�ȭ' NOT NULL, -- �ʻ�ȭ, �߻�ȭ, ǳ��, ����, Ÿ�����׷���, �Ϸ���Ʈ, ������, �˾�Ʈ, ������, ī��
    work_subject_name    VARCHAR2(50)      DEFAULT '��' NOT NULL,  -- ��, ����, ����, �ܿ�, ����, �Ĺ�, ����, ���, �ǳ�
    work_img_url         VARCHAR2(300)     NOT NULL, 
    work_content         VARCHAR2(1000)    NOT NULL, 
    work_sale            NUMBER(4,2)       DEFAULT 0.00 NOT NULL, -- ���� �ۼ�Ʈ
    work_isBest          NUMBER(1)         DEFAULT 0 NOT NULL, 
    work_reg_date        DATE              DEFAULT sysdate NOT NULL, 
    work_price           NUMBER(7)         DEFAULT 10000 NOT NULL, 
    work_dp              VARCHAR2(500)     DEFAULT 'https://www.google.com/maps?cid=13363865620386383060' NULL, 
    work_hit             NUMBER(4)         NOT NULL, 
    admin_id             NUMBER(3)         NULL, 
     PRIMARY KEY (id)
);

-- Auto Increment�� ���� Sequence �߰� SQL - Work.id
CREATE SEQUENCE Work_SEQ
START WITH 1
INCREMENT BY 1;

--------------- Artist�۰� ���̺�, artist_seq ����
CREATE TABLE Artist
(
    id                     NUMBER(4)         NOT NULL, 
    artist_korean_name     VARCHAR2(300)     NOT NULL, 
    artist_english_name    VARCHAR2(300)     NOT NULL, 
    artist_country         VARCHAR2(100)     NOT NULL, 
    artist_birth_death     VARCHAR2(100)     NOT NULL, 
    artist_main            VARCHAR2(200)     NOT NULL, 
    artist_content         VARCHAR2(2000)    NOT NULL, 
     PRIMARY KEY (id)
);

-- Auto Increment�� ���� Sequence �߰� SQL - Artist.id
CREATE SEQUENCE Artist_SEQ
START WITH 1
INCREMENT BY 1;

-- drop table artist CASCADE CONSTRAINTS;
--alter table artist modify artist_content varchar2(2000);
--------------- Admin ������ ���̺�, admin_seq �����ؾ�
CREATE TABLE Admin
(
    id                NUMBER(3)       NOT NULL, 
    admin_name        VARCHAR2(30)    NOT NULL, 
    admin_id          VARCHAR2(20)    NOT NULL, 
    admin_pw          VARCHAR2(20)    NOT NULL, 
    admin_isSuper     NUMBER(1)       DEFAULT 0 NOT NULL, 
    admin_phone       VARCHAR2(20)    NOT NULL, 
    admin_reg_date    DATE            NOT NULL, 
    admin_ban         NUMBER(1)       DEFAULT 0 NOT NULL, 
     PRIMARY KEY (id)
);

-- Auto Increment�� ���� Sequence �߰� SQL - Admin.id
CREATE SEQUENCE Admin_SEQ
START WITH 1
INCREMENT BY 1;


------- �ܷ�Ű �Ѳ����� ����
-- Work(admin_id) -> Admin(id)
ALTER TABLE Work
    ADD CONSTRAINT FK_Work_admin_id_Admin_id FOREIGN KEY (admin_id)
        REFERENCES Admin (id) ;

-- Work(artist_id) -> Artist(id)
ALTER TABLE Work
    ADD CONSTRAINT FK_Work_artist_id_Artist_id FOREIGN KEY (artist_id)
        REFERENCES Artist (id) ;

--- 1. work, artist, admin ���̺� �� seq�� ���� �� -----




