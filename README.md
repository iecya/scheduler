# Scheduler

Simple app to determine the next task run given a list of Cron schedules.

## Dependencies

* [Leiningen](https://leiningen.org/) (optional - needed to run the app using `lein` command line)


## Usage

To use the application, please run one of the following commands:

java:
```
echo <STDIN> | bin/run.sh <time>
```

or 

```
echo <STDIN> | /usr/bin/java -jar #{PATH_TO_ROOT_DIR}/target/uberjar/scheduler-0.1.0-standalone.jar <time>
```

---


lein (if Leiningen is installed):
```
echo <STDIN> | lein run <time>
```

or 

```
lein run <time>
```
The last command will start the application and allow the user to manually input Cron schedules; to exit the application just input `exit`

### Tests

It is possible to test the application against custom inputs. To do so, create a file in `resources/inputs` with the `input` word in the name, containing a time input in the first line (in the HH:MM format), followed by a list of Cron schedules; then create a file in the `resources/outputs` with a name matching the correspondent input file (just replace `input` with `output`) containing a list of expected output.
Formats and examples below:

Time format
```
HH:MM
// 18:37
```

Input Cron schedule
```
MM|* HH|* /bin/<script_name>
// 10 2 /bin/my_script
// * 20 /bin/my_script
// 30 * /bin/my_script
// * * /bin/my_script
```

Output expected (based on the input example above)
```
HH:MM today|tomorrow - /bin/<script_name>
// 2:10 tomorrow - /bin/my_script
// 20:00 today - /bin/my_script
// 19:30 today - /bin/my_script
// 18:37 today - /bin/my_script
```



## License

Copyright © 2019

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
