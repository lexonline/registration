INSERT INTO USER (ID,FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD,PHONE,SALARY,MEMBERTYPE) VALUES ('201907010000','admin', 'admin', 'admin@lexrizen.com', 'supersecret','00000000',1,'ADMIN');
INSERT INTO USER (ID,FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD,PHONE,SALARY,MEMBERTYPE) VALUES ('201907019999','lex', 'rizen', 'lexrizen@hotmail.com', 'secret1','0810009999',9999,'ADMIN');



INSERT INTO OAUTH_CLIENT_DETAILS (CLIENT_ID, RESOURCE_IDS, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ADDITIONAL_INFORMATION, AUTOAPPROVE) VALUES ('android', 'some_secure_resource', 'mobile_rw', 'password,refresh_token', '', '', '{}', '');
INSERT INTO OAUTH_CLIENT_DETAILS (CLIENT_ID, RESOURCE_IDS, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ADDITIONAL_INFORMATION, AUTOAPPROVE) VALUES ('webclient', 'some_secure_resource', 'webclient_rw', 'password,refresh_token', '', '', '{}', '');
