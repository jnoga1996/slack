# slack

# How to run
- Download Intellij Ultimate Edition.
- Clone repository into selected directory.
- Run Intellij and click open project, then navigate to cloned dir and select pom.xml -> open as project.
- Wait for dependencies and libs to finish downloading.
- Run app (Shift + F10)
- To check if application work go to localhost:8080/Messages/Messages

# Available API url requests:
Url - return type - description
Get:
- /Attendance/All - List<AttendanceList> - returns all attendances from db.
- /Attendance/?date=2019-12-30 - List<AttendanceList> - returns attendance list for specified date (pattern: "yyyy-MM-dd").
- /Users/All - List<User> - returns all users from db.
- /Users/?login=<login>&password=<password> - User - return user object if credentials are correct, thows exception otherwise.
- /Courses/ - List<Course> - returns all courses from db.
- /Course/{id} - Course - returns course with specified id from db.
- /Activity/ - List<Activity> - returns all activities from db.
- /Activity/{id} - Activity - returns activity with specified id from db.
- /TimePlan/?activity=<activity> - TimePlan - returns time plan by specified activity.
- /TimePlan/{id} - TimePlan - returns time plan with specified id from db.
- /Files/ - List<String> - returns all available files.
- /Files/{id} - String - returns all available files for course with specified id.

Post:
- /Attendance/ - please take a look at comment in AttendanceController class to see how to send post request.
- /Users/ - HttpStatus Ok - adds new user to db.
- /Courses/ - HttpStatus Ok - adds new course to db.
- /Activity/ - HttpStatus Ok - adds new activity to db.
- /TimePlan/ - HttpStatus Ok - adds new time plan to db.
- /Files/?file=<file> - HttpStatus Ok - upload file to server.
- /Files/{course_id}/?file=<file> - HttpStatus Ok - uploads file related to specified course to server.

Delete:
- /Attendance/{id} - HttpStatus Ok or Not found - removes entry from db.
- /Users/{id} - HttpStatus Ok or Not found - removes entry from db.
- /Courses/{id} - HttpStatus Ok or Not found - removes entry from db.
- /Activity/{id} - HttpStatus Ok or Not found - removes entry from db.
- /TimePlan/{id} - HttpStatus Ok or Not found - removes entry from db.
- /Delete/{filename} - HttpStatus Ok or Not found - removes file from server. When specifying filename - file extension should be omitted.

Name of files saved to server looks like this <course_id>.<filename> for example 33.abcd.txt

Post request examples:
```
Course
content-type : application/json
body : 
{
		    "id" : 3,
        "name": "TEST2",
        "description": "TEST",
        "startDate": "2019-11-18",
        "endDate": "2020-03-02",
        "users": [
            {
                "id": 2,
                "login": "student",
                "password": "student",
                "role": "USER",
                "faculty": "Faculty of Law",
                "major": "Law",
                "semester": 3
            },
            {
                "id": 3,
                "login": "student2",
                "password": "student2",
                "role": "USER",
                "faculty": "Faculty of Physics and Astronomy",
                "major": "Applied computer science",
                "semester": 1
            }
        ]
    }
    
Attendance
content-type : application/json
body : 
{
	"dateString":"2019-12-31",
	"presentUsers":{"1":true,"2":true,"3":true}
}
```

Be aware that ids of presentUsers should be available in db otherwise post operation will fail.
