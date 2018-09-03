create table operation_log
(
   log_datetime varchar(17) not null,
   user_id varchar(255) not null,
   type varchar(255),
   system_scope varchar(255),
   ip_address varchar(255),
   token varchar(255),
   thread_id varchar(255),
   function_name varchar(255),
   event_name varchar(255),
   start_stop varchar(255),
   end_result varchar(max)
);
