/*
https://leetcode.com/problems/employees-earning-more-than-their-managers/
Easy.

The Employee table holds all employees including their managers.
Every employee has an Id, and there is also a column for the manager Id.

+----+-------+--------+-----------+
| Id | Name  | Salary | ManagerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | NULL      |
| 4  | Max   | 90000  | NULL      |
+----+-------+--------+-----------+

Given the Employee table, write a SQL query that finds out employees who earn more than
their managers. For the above table, Joe is the only employee who earns more than his manager.

+----------+
| Employee |
+----------+
| Joe      |
+----------+
*/

SELECT `e`.`Name` AS `Employee`
FROM `Employee` AS `e`,
     `Employee` AS `b`
WHERE `e`.`ManagerId` = `b`.`Id`
  AND `e`.`Salary` > `b`.`Salary`;


SELECT `e`.`NAME` AS `Employee`
FROM (`Employee` AS `e`
         INNER JOIN `Employee` AS `m`
                    ON (`e`.`ManagerId` = `m`.`Id`
                        AND `e`.`Salary` > `m`.`Salary`));
