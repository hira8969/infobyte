# Test Cases

| ID | Scenario | Steps | Expected Result |
|----|----------|-------|-----------------|
| TC01 | Login Success | Enter `1001` and `1234` | Login successful |
| TC02 | Login Failure | Enter valid user and wrong PIN | Invalid credentials message |
| TC03 | 3 Failed Attempts | Enter wrong PIN 3 times | Session locked |
| TC04 | Deposit Success | Deposit `5000` | Balance increases and transaction is logged |
| TC05 | Deposit Invalid Amount | Deposit `0` | Error message |
| TC06 | Withdraw Success | Withdraw valid amount | Balance decreases and transaction is logged |
| TC07 | Withdraw Insufficient Funds | Withdraw more than balance | Insufficient funds message |
| TC08 | Transfer Success | Transfer to `1002` | Sender balance decreases, receiver balance increases |
| TC09 | Transfer Invalid Account | Transfer to `9999` | Recipient account not found |
| TC10 | Transfer Insufficient Funds | Transfer more than balance | Insufficient funds message |
| TC11 | Balance Inquiry | Select balance menu | Current and available balance shown |
| TC12 | Transaction History | Perform operations and view history | Transactions are listed |
| TC13 | Logout | Select logout | User returns to login screen |
| TC14 | Exit | Select exit | Application terminates |
