# Test Cases

| ID | Scenario | Steps | Expected Result |
| --- | --- | --- | --- |
| TC01 | Login success | Enter `student` and `student123`, click Login | Dashboard opens |
| TC02 | Login failure | Enter invalid credentials | Error message appears |
| TC03 | Empty username | Leave username blank | Username required message appears |
| TC04 | Empty password | Leave password blank | Password required message appears |
| TC05 | Profile update | Enter valid display name and password | Success message appears |
| TC06 | Invalid profile name | Save blank display name | Validation error appears |
| TC07 | Invalid password | Save password below 6 chars | Validation error appears |
| TC08 | Next navigation | Start exam and click Next | Next question is displayed |
| TC09 | Previous navigation | Move forward, then Previous | Previous question is displayed |
| TC10 | Answer saving | Select answer, navigate away, return | Selected answer is retained |
| TC11 | Manual submission | Click Submit and confirm Yes | Result screen opens |
| TC12 | Unanswered warning | Submit with unanswered questions | Warning appears before confirmation |
| TC13 | Timer expiry | Let timer reach zero | Exam auto-submits |
| TC14 | Result calculation | Submit known correct answers | Score and statistics are accurate |
| TC15 | Logout | Click Logout | Login screen opens and session clears |
| TC16 | Window close protection | Close dashboard during exam | Confirmation dialog appears |
