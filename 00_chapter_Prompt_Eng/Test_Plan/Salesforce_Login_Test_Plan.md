# Salesforce Login Page Functional Test Plan

## 1. Test Plan ID and Title

- Test Plan ID: TP-SF-LOGIN-001
- Title: Salesforce Login Page Functional Test Plan

---

## 2. Objective and References

### Objective
Validate the Salesforce login page behavior for the agreed login scope, focused on page rendering, field validation, authentication outcomes, and the observable invalid-login error state using the public page at https://login.salesforce.com/?locale=in.

### References
- Salesforce login page
- Generic QA template for test planning
- Salesforce login workflow requirements captured in the prompt context

### Scope Decision
This plan covers the login page UI and validation only, as agreed in the clarification step.

### Assumptions
- The application under test is the public Salesforce login page.
- Valid credentials are not currently available; a valid test account will be required for positive execution.
- Exact validation text is required in the plan and should be confirmed against the live UI or approved acceptance criteria.

---

## 3. In Scope and Out of Scope

### In Scope
- Login page render and field visibility
- Username/email field entry
- Password field entry
- Submit action
- Valid user login flow
- Invalid user login flow
- Empty or invalid field validation
- Remember Me checkbox visibility and interaction
- Basic regression checks on the login form

### Out of Scope
- Password reset
- MyDomain or custom domain login
- SSO, MFA, or IDP-based authentication
- Account creation or profile management
- API-level authentication checks
- Performance, security, and accessibility audits beyond the login flow

---

## 4. Requirements and Planned Coverage

| Requirement ID | Requirement | Scenario Type | Planned Coverage |
| --- | --- | --- | --- |
| RQ-01 | The login page loads and displays username, password, and login controls | UI / Functional | Verify page render and required elements are visible |
| RQ-02 | A valid user can sign in successfully | Positive Functional | Execute successful login with a valid account |
| RQ-03 | Invalid credentials are rejected and do not create a valid session | Negative Functional | Attempt login with incorrect values |
| RQ-04 | Empty required fields are rejected | Negative / Boundary | Submit blank username and/or password |
| RQ-05 | Remember Me checkbox is visible and selectable | UI Interaction | Verify checkbox behavior |
| RQ-06 | A clear login error state is shown on invalid credentials | Validation / UX | Verify the error message or visible failure state |
| RQ-07 | Authentication failure does not create an authenticated session | Negative Functional | Confirm the user remains unauthenticated |

### Coverage Notes
- The plan includes positive, negative, and boundary cases within the approved scope.
- Exact validation text must be checked against the live application or an approved requirement before final sign-off.
- This plan intentionally excludes unrelated Salesforce flows beyond login validation.

---

## 5. Test Approach, Levels, and Types

### Test Approach
A functional login validation approach will be used:
1. Confirm render and visibility of login form elements
2. Validate a successful login with known-good credentials
3. Validate failure with invalid credentials
4. Validate empty or malformed input behavior
5. Run a small regression check for the login form

### Test Levels
- UI / component level: login page fields and actions
- Feature level: authentication decision and validation outcome
- Regression level: login flow health after a change

### Test Types
- Functional testing
- Negative testing
- Boundary validation
- UI verification
- Regression testing

---

## 6. Environment, Tools, Access, and Test Data

### Environment
- URL: https://login.salesforce.com/?locale=in
- Browser: Latest stable Chrome on desktop
- OS: Desktop environment, details not provided
- Environment type: public Salesforce login page / project test environment as applicable

### Tools
- Selenium WebDriver
- Java + Maven + TestNG
- Browser automation tooling
- Reporting or defect management tool if used in the project

### Access
- Access to the Salesforce login page is available
- Valid credentials for positive login: Not provided
- Admin or environment access: Not provided

### Test Data
- Valid username and password: Not provided
- Invalid login example: invalid.user@test.com / WrongPassword123!
- Empty credentials: blank username and/or password
- Malformed email: invalid-email-format
- Whitespace-only input examples

### Data Handling Rules
- Real credentials must not be embedded in test artifacts.
- Credentials should be provided through environment variables or a secure approved mechanism.

---

## 7. Entry and Exit Criteria

### Entry Criteria
- Salesforce login page is reachable
- Browser dependencies are installed and working
- Required test data is available or approved synthetic values are used
- Test scope is approved

### Exit Criteria
- All in-scope login scenarios are executed or explicitly marked blocked
- Defects are logged with evidence
- Validation results are reviewed against expected behavior
- No unresolved critical issue remains for the approved scope

---

## 8. Roles, Responsibilities, Estimates, and Schedule

| Role | Responsibilities |
| --- | --- |
| QA Lead | Approve scope and review the test plan |
| QA Engineer | Execute test cases, validate behavior, report findings |
| Automation Engineer | Implement automation for the approved login scenarios |
| Developer | Investigate defects and validate fixes |
| Product Owner / BA | Confirm business acceptance and expected behavior |

### Estimate
- Test planning and review: 1 session
- Functional validation: 1 to 2 hours
- Automation for the approved scenarios: 1 to 2 hours depending on environment readiness
- Defect triage and retest: variable

### Schedule
- Exact dates are Not provided.
- Timing depends on environment availability and credentials.

---

## 9. Defect Management and Reporting

### Defect Reporting
Each defect should include:
- Test ID
- Summary
- Severity
- Priority
- Steps to reproduce
- Expected result
- Actual result
- Evidence / screenshot
- Status

### Severity and Priority
- Severity and priority should be proposed based on business impact unless confirmed by the team.
- A login issue blocking authentication is typically high severity and high priority.

### Reporting Cadence
- Daily triage during execution if active
- Final review at the end of each cycle

---

## 10. Risks, Dependencies, Assumptions, and Open Questions

### Risks
- UI may change without notice
- Exact error text may vary by region or configuration
- Browser differences may affect rendering or interaction
- Positive credentials may not be available for execution

### Dependencies
- Valid test account for positive validation
- Browser and WebDriver compatibility
- Environment availability
- Stable target page during execution

### Assumptions
- Scope is limited to the login page and basic validation
- Exact validation text will be checked against the live page or approved business requirements
- Broader Salesforce flows such as custom domain, password reset, SSO, and MFA are outside scope

### Open Questions
- What exact browser version is required?
- Are valid credentials available for execution?
- Is exact error text a hard acceptance requirement, or is observable failure enough?
- Should future phases include password reset or custom domain login?

---

## 11. Suspension and Resumption Criteria

### Suspension Criteria
- The login page is unavailable
- Browser automation is not working
- Valid credentials are unavailable
- The environment is unstable or unusable

### Resumption Criteria
- Environment is restored
- Browser automation is working again
- Test data is ready
- The blocker is resolved or explicitly waived

---

## 12. Test Deliverables and Approval

### Deliverables
- Approved test plan
- Requirement-to-coverage matrix
- Detailed test cases for valid and invalid login scenarios
- Defect log and execution summary
- Automation project for the approved two-scenario scope

### Approval Status
- Plan is approved for the agreed scope and saved in the Test_plan folder.
- Final sign-off should confirm:
  - Browser target
  - Credential availability
  - Acceptance criteria for exact validation text
  - Whether to expand scope in a future phase

---

## 13. Summary

This test plan covers the approved Salesforce login page scope using the generic QA template and the clarification answers captured during planning. It focuses on page rendering, required field behavior, valid-login validation, invalid-login handling, and observable failure states.

The plan is intentionally limited to the login page and does not assume any broader Salesforce account or authentication workflow beyond the agreed requirement.
