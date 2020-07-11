### Advanced options

Use `chlogen --help` to print all available options.

***

* `--user <userName>` - target github repo owner username
* `--project <projectName>` - target github project name
* `--token <token>` - personal github token (has to be used if no. requests / hour > 50)
* `--date-format <format>` - custom date format
* `--output <filename>` - custom output filename; if left blank `stdout` will be used. 
* `--base-file <filename>` - appends changes to the specified file
* `--[no-]issues` - include closed issues
* `--[no-]pull-requests` - include merged pull-requests
* `--[no-]append-author` - appends PR author's name
* `--[no-]verbose`
