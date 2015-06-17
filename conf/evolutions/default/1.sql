# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table configurations (
  configuration_id          bigint auto_increment not null,
  page_title                varchar(255),
  page_header               varchar(255),
  page_width                integer,
  page_border               tinyint(1) default 0,
  page_background_image     varchar(255),
  page_headline_backgroundcolor varchar(255),
  page_navigation_backgroundcolor varchar(255),
  page_content_backgroundcolor varchar(255),
  page_footer_backgroundcolor varchar(255),
  page_backgroundimage      varchar(255),
  page_footer_left          TEXT,
  page_footer_right         TEXT,
  theme                     varchar(255),
  meta_charset              varchar(255),
  meta_author               varchar(255),
  meta_description          varchar(255),
  meta_keywords             varchar(255),
  meta_date                 date,
  email_config_email_id     bigint,
  multiple_languages        tinyint(1) default 0,
  impressum                 TEXT,
  privacy                   TEXT,
  contact                   TEXT,
  constraint pk_configurations primary key (configuration_id))
;

create table courses (
  course_id                 bigint auto_increment not null,
  title                     varchar(255),
  description               TEXT,
  slots                     integer,
  event_id                  bigint,
  constraint pk_courses primary key (course_id))
;

create table emails (
  email_id                  bigint auto_increment not null,
  display                   tinyint(1) default 0,
  subject                   varchar(255),
  from_name                 varchar(255),
  from_email                varchar(255),
  add_tnb                   tinyint(1) default 0,
  add_attachement           tinyint(1) default 0,
  body_text                 TEXT,
  configuration_configuration_id bigint,
  constraint pk_emails primary key (email_id))
;

create table events (
  event_id                  bigint auto_increment not null,
  name                      varchar(255),
  shortcode                 varchar(255),
  description               TEXT,
  description_image         varchar(255),
  start_date                date,
  end_date                  date,
  support_email             varchar(255),
  facebook                  varchar(255),
  twitter                   varchar(255),
  googleplus                varchar(255),
  configuration_configuration_id bigint,
  constraint pk_events primary key (event_id))
;

create table formdata (
  data_id                   bigint auto_increment not null,
  name                      varchar(255),
  typ                       varchar(255),
  icon                      varchar(255),
  display                   tinyint(1) default 0,
  required                  tinyint(1) default 0,
  template_id               bigint,
  constraint pk_formdata primary key (data_id))
;

create table participants (
  participant_id            bigint auto_increment not null,
  salutation                varchar(255),
  lastname                  varchar(255),
  firstname                 varchar(255),
  birthday                  date,
  badge_firstname           varchar(255),
  badge_lastname            varchar(255),
  company                   varchar(255),
  company_additional        varchar(255),
  department                varchar(255),
  service_cost_center       varchar(255),
  position                  varchar(255),
  vatin                     varchar(255),
  street                    varchar(255),
  postofficebox             varchar(255),
  postcode                  integer,
  location                  varchar(255),
  country                   varchar(255),
  phone                     varchar(255),
  mobilephone               varchar(255),
  telefax                   varchar(255),
  email                     varchar(255),
  url                       varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  password_confirmation     varchar(255),
  billingsalutation         varchar(255),
  billinglastname           varchar(255),
  billingfirstname          varchar(255),
  billingcompany            varchar(255),
  billingcompany_additional varchar(255),
  billingdepartment         varchar(255),
  billingservice_cost_center varchar(255),
  billingvatin              varchar(255),
  billingstreet             varchar(255),
  billingpostcode           integer,
  billinglocation           varchar(255),
  billingcountry            varchar(255),
  billingemail              varchar(255),
  shippingsalutation        varchar(255),
  shippinglastname          varchar(255),
  shippingfirstname         varchar(255),
  shippingcompany           varchar(255),
  shippingcompany_additional varchar(255),
  shippingdepartment        varchar(255),
  shippingstreet            varchar(255),
  shippingpostcode          integer,
  shippinglocation          varchar(255),
  shippingcountry           varchar(255),
  event_id                  bigint,
  constraint uq_participants_email unique (email),
  constraint uq_participants_username unique (username),
  constraint pk_participants primary key (participant_id))
;

create table participant_course_service (
  participant_participant_id bigint,
  course_course_id          bigint)
;

create table participant_ticket_service (
  participant_participant_id bigint,
  ticket_ticket_id          bigint)
;

create table security_role (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_security_role primary key (id))
;

create table templates (
  template_id               bigint auto_increment not null,
  name                      varchar(255),
  display                   tinyint(1) default 0,
  login                     tinyint(1) default 0,
  registration              tinyint(1) default 0,
  password_login            tinyint(1) default 0,
  passwords                 varchar(255),
  configuration_id          bigint,
  constraint pk_templates primary key (template_id))
;

create table tickets (
  ticket_id                 bigint auto_increment not null,
  title                     varchar(255),
  description               TEXT,
  price                     double,
  minpp                     integer,
  maxpp                     integer,
  contingent                integer,
  duration                  integer,
  img                       varchar(255),
  event_id                  bigint,
  constraint pk_tickets primary key (ticket_id))
;

create table token (
  token                     varchar(255) not null,
  user_id                   bigint,
  type                      varchar(8),
  date_creation             datetime,
  email                     varchar(255),
  constraint ck_token_type check (type in ('password','email')),
  constraint pk_token primary key (token))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  email                     varchar(255),
  password_hash             varchar(255),
  date_creation             datetime,
  validated                 tinyint(1) default 0,
  constraint uq_user_username unique (username),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create table user_permission (
  id                        bigint auto_increment not null,
  permission_value          varchar(255),
  constraint pk_user_permission primary key (id))
;


create table user_security_role (
  user_id                        bigint not null,
  security_role_id               bigint not null,
  constraint pk_user_security_role primary key (user_id, security_role_id))
;

create table user_user_permission (
  user_id                        bigint not null,
  user_permission_id             bigint not null,
  constraint pk_user_user_permission primary key (user_id, user_permission_id))
;
alter table configurations add constraint fk_configurations_emailConfig_1 foreign key (email_config_email_id) references emails (email_id) on delete restrict on update restrict;
create index ix_configurations_emailConfig_1 on configurations (email_config_email_id);
alter table courses add constraint fk_courses_event_2 foreign key (event_id) references events (event_id) on delete restrict on update restrict;
create index ix_courses_event_2 on courses (event_id);
alter table emails add constraint fk_emails_configuration_3 foreign key (configuration_configuration_id) references configurations (configuration_id) on delete restrict on update restrict;
create index ix_emails_configuration_3 on emails (configuration_configuration_id);
alter table events add constraint fk_events_configuration_4 foreign key (configuration_configuration_id) references configurations (configuration_id) on delete restrict on update restrict;
create index ix_events_configuration_4 on events (configuration_configuration_id);
alter table formdata add constraint fk_formdata_template_5 foreign key (template_id) references templates (template_id) on delete restrict on update restrict;
create index ix_formdata_template_5 on formdata (template_id);
alter table participants add constraint fk_participants_event_6 foreign key (event_id) references events (event_id) on delete restrict on update restrict;
create index ix_participants_event_6 on participants (event_id);
alter table participant_course_service add constraint fk_participant_course_service_participant_7 foreign key (participant_participant_id) references participants (participant_id) on delete restrict on update restrict;
create index ix_participant_course_service_participant_7 on participant_course_service (participant_participant_id);
alter table participant_course_service add constraint fk_participant_course_service_course_8 foreign key (course_course_id) references courses (course_id) on delete restrict on update restrict;
create index ix_participant_course_service_course_8 on participant_course_service (course_course_id);
alter table participant_ticket_service add constraint fk_participant_ticket_service_participant_9 foreign key (participant_participant_id) references participants (participant_id) on delete restrict on update restrict;
create index ix_participant_ticket_service_participant_9 on participant_ticket_service (participant_participant_id);
alter table participant_ticket_service add constraint fk_participant_ticket_service_ticket_10 foreign key (ticket_ticket_id) references tickets (ticket_id) on delete restrict on update restrict;
create index ix_participant_ticket_service_ticket_10 on participant_ticket_service (ticket_ticket_id);
alter table templates add constraint fk_templates_configuration_11 foreign key (configuration_id) references configurations (configuration_id) on delete restrict on update restrict;
create index ix_templates_configuration_11 on templates (configuration_id);
alter table tickets add constraint fk_tickets_event_12 foreign key (event_id) references events (event_id) on delete restrict on update restrict;
create index ix_tickets_event_12 on tickets (event_id);



alter table user_security_role add constraint fk_user_security_role_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_security_role add constraint fk_user_security_role_security_role_02 foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;

alter table user_user_permission add constraint fk_user_user_permission_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_user_permission add constraint fk_user_user_permission_user_permission_02 foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table configurations;

drop table courses;

drop table emails;

drop table events;

drop table formdata;

drop table participants;

drop table participant_course_service;

drop table participant_ticket_service;

drop table security_role;

drop table templates;

drop table tickets;

drop table token;

drop table user;

drop table user_security_role;

drop table user_user_permission;

drop table user_permission;

SET FOREIGN_KEY_CHECKS=1;

