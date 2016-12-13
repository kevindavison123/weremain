CREATE TABLE IF NOT EXISTS
    USERSDB(
    ID              BIGINT  IDENTITY(1000,1)    UNIQUE NOT NULL,
    NAME            VARCHAR(70)                 NOT NULL,
    EMAIL           VARCHAR(MAX)                UNIQUE NOT NULL,
    PHONENUMBER     VARCHAR(28)                 NOT NULL,
    JOINED          DATETIME                    NOT NULL        DEFAULT GETDATE()       CONSTRAINT CURRENT_GETDATE_USERSDB,
    UPDATED         DATETIME                    NULL            DEFAULT GETDATE()       CONSTRAINT CURRENT_GETDATE_USERSDB,
    CHARITIES       VARCHAR(MAX)                NULL,
    CONTENT         VARCHAR(MAX)
    );