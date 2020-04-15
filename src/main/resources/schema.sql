drop table if exists oauth_client_token;
drop table if exists oauth_client_details;
drop table if exists oauth_access_token;
drop table if exists oauth_refresh_token;
drop table if exists authority;
drop table if exists user;
drop table if exists user_status;
drop table if exists user_authorities;
drop table if exists oauth_code;
drop table if exists oauth_approvals;


create table oauth_client_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);


CREATE TABLE oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity integer(11) DEFAULT NULL,
  refresh_token_validity integer(11) DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);


create table `oauth_access_token` (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGBLOB,
  refresh_token VARCHAR(255)
);


create table `oauth_refresh_token`(
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication LONGBLOB
);


CREATE TABLE authority (
  id  integer,
  authority varchar(255),
  primary key (id)
);

CREATE table user_status
(
    id int primary key,
    user_status varchar(20) not null
);

create table user
(
	id int primary key,
	user_name varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	name varchar(255) not null,
	surname varchar(255) not null,
	status int not null,
	constraint fk_user_status
		foreign key (status) references user_status (id),
	constraint unique_username_and_email unique (user_name,email)
);

CREATE TABLE user_authorities (
  user_id bigint not null,
  authorities_id bigint not null
);


create table oauth_code (
  code VARCHAR(255), authentication VARBINARY(255)
);


create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);