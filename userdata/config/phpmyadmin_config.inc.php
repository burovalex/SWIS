<?php
$i = 1;
$cfg['Servers'][$i]['verbose'] = '';
$cfg['Servers'][$i]['host'] = '%ip%';
$cfg['Servers'][$i]['port'] = %mysqlport%;
$cfg['Servers'][$i]['socket'] = '';
$cfg['Servers'][$i]['connect_type'] = 'tcp';
$cfg['Servers'][$i]['compress'] = false;
$cfg['Servers'][$i]['extension'] = 'mysqli';
$cfg['Servers'][$i]['auth_type'] = 'cookie';
$cfg['Servers'][$i]['AllowRoot'] = true;
$cfg['Servers'][$i]['nopassword'] = true;
$cfg['Servers'][$i]['AllowNoPassword'] = true;
$cfg['ServerLibraryDifference_DisableWarning'] = true;
$cfg['blowfish_secret'] = 'r3a30e4ed1cfbfdds22379';
$cfg['ShowAll'] = true;
$cfg['TableNavigationLinksMode'] = 'icons';
$cfg['ActionLinksMode'] = 'icons';
$cfg['TabsMode'] = 'both';
$cfg['VersionCheck'] = false;
$cfg['AjaxEnable'] = true;
$cfg['TempDir'] = '%sprogdir%/userdata/temp';
$cfg['SaveDir'] = '%sprogdir%/userdata/temp';
$cfg['UploadDir'] = '%sprogdir%/userdata/temp';
$cfg['ServerDefault'] = 1;
#$cfg['ThemeManager'] = false;
$cfg['ThemeDefault']= 'original';
$cfg['MaxRows'] = 50;
/**
 * disallow editing of binary fields
 * valid values are:
 *   false    allow editing
 *   'blob'   allow editing except for BLOB fields
 *   'noblob' disallow editing except for BLOB fields
 *   'all'    disallow editing
 * default = blob
 */
//$cfg['ProtectBinary'] = 'false';
$cfg['DefaultLang'] = 'ru';
/**
* default display direction (horizontal|vertical|horizontalflipped)
*/
$cfg['DefaultDisplay']      = 'horizontal'; 
/**
 * How many columns should be used for table display of a database?
 * (a value larger than 1 results in some information being hidden)
 * default = 1
 */
//$cfg['PropertiesNumColumns'] = 2;
$cfg['PmaNoRelation_DisableWarning'] = true;
?>