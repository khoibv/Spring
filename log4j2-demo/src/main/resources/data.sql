insert into operation_log
(
  log_datetime,
  user_id,
  type,
  system_scope,
  ip_address,
  token,
  thread_id,
  function_name,
  event_name,
  start_stop,
  end_result
)
values
(
  '20180701000000000',
  'Earth',
  '画面',
  '内部',
  '192.168.99.100',
  'VzIwMTgwNjIwMTYyNzExMDUyMTcyMDIwMTA4MDYxRWFydGggICAgICAgICAgICAgICAgICAgICAgICAgOUY1RjY0QkM2RUFENjVCN0Q3NzVEODJBRDRFOUQwNTY=',
  '12345',
  'SCRF001_ログイン画面',
  'ログイン',
  '終了',
  '{ loginStatus: success, .... }'
);
