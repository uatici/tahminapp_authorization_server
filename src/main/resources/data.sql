INSERT INTO authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_PRODUCT_ADMIN');

INSERT INTO user_status  VALUES(1,'ENABLE');
INSERT INTO user_status VALUES(2,'DISABLE');
INSERT INTO user_status VALUES(3,'VERIFY');

INSERT INTO user VALUES(1,'oauth_admin', 'oauth@oauth.com','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','oauth','admin',1);
INSERT INTO user VALUES(2,'resource_admin', 'resource_@resource_.com','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','resource','admin',2);
INSERT INTO user  VALUES(3,'product_admin', 'product@product.com','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','product','admin',3);
INSERT INTO user_authorities VALUE (1,1);
INSERT INTO user_authorities VALUE (2,2);
INSERT INTO user_authorities VALUE (3,3);


INSERT INTO oauth_client_details VALUES('curl_client','product_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'client_credentials,password', 'http://127.0.0.1', 'ROLE_PRODUCT_ADMIN', 7200, 0, NULL, 'true');
INSERT INTO oauth_client_details VALUES('ufuk','product_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'client_credentials,implicit,authorization_code,refresh_token,password', 'http://127.0.0.1', 'ROLE_PRODUCT_ADMIN', 7200, 7200, NULL, 'true');
