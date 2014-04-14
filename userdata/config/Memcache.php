<?php
return array (
  'stats_api' => 'Server',
  'slabs_api' => 'Server',
  'items_api' => 'Server',
  'get_api' => 'Server',
  'set_api' => 'Server',
  'delete_api' => 'Server',
  'flush_all_api' => 'Server',
  'connection_timeout' => '1',
  'max_item_dump' => '100',
  'refresh_rate' => 5,
  'memory_alert' => '59',
  'hit_rate_alert' => '90',
  'eviction_alert' => '0',
  'file_path' => '%sprogdir%/userdata/temp/',
  'servers' =>
  array (
    'Default' =>
    array (
      '%ip%:11211' =>
      array (
        'hostname' => '%ip%',
        'port' => '11211',
      ),
    ),
  ),
);