INSERT INTO USERSDB(
    NAME,
    EMAIL,
    PHONENUMBER,
    CHARITIES,
    JOINED,
    UPDATED,
    CONTENT
)
VALUES(
'KEVIN',
'KEVIN@KEVIN.COM',
'734-256-2078',
'["CHARITY1","CHARITY2","CHARITY3"]',
TO_DATE('10/10/2012', 'MM/dd/yyyy'),
TO_DATE('10/10/2012', 'MM/dd/yyyy'),
'{"id":1000,"name":"KEVIN","joined":1349841600000,"updated":1349841600000,"charities":"[\"CHARITY1\",\"CHARITY2\",\"CHARITY3\"]","email":"KEVIN@KEVIN.COM","phoneNumber":"734-256-2078"}'
);

INSERT INTO USERSDB(
    NAME,
    EMAIL,
    PHONENUMBER,
    CHARITIES,
    JOINED,
    UPDATED,
    CONTENT

)
VALUES(
'AARON',
'AARON@AARON.COM',
'734-555-6666',
'["CHARITY1","CHARITY2","CHARITY3"]',
TO_DATE('10/11/2012', 'MM/dd/yyyy'),
TO_DATE('10/11/2012', 'MM/dd/yyyy'),
'{"id":1001,"name":"AARON","joined":1349928000000,"updated":1349928000000,"charities":"[\"CHARITY1\",\"CHARITY2\",\"CHARITY3\"]","email":"AARON@AARON.COM","phoneNumber":"734-555-6666"}'
);