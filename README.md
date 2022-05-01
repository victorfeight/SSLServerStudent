tl;dr: 
To let this application run in HTTPS, I needed a certificate.

**In production-grade applications, certificates are issued from renowned Certification Authorities (CA) to ensure that our application is a trusted entity.**

However, as this is an example, I'll use a Self-Signed Certificate for the application.

We can create one using Java **keytool** utility to create and manage certificates locally.

keytool is available in JDK_HOME/bin directory.

------

Briefly summarize your client, Artemis Financial, and their software requirements. Who was the client? What issue did they want you to address?
Artemis

Points of consideration: This company deals directly with customers delivering highly sensitive data including financial plans for savings, retirement, investments, and insurance. Any of these plans may contain data such as social security, financial information, addresses, numbers that shouldn’t be leaked or able to be extracted. In accordance with OWASP, encryption is highly recommended for transmission of such sensitive information, by ensuring a secure protocol such as HTTPS is used to secure data communication between server and client; this prevents man in the middle attacks. Any server side code should be protected, no information should be stored or accessible from logs. Secure communication is a necessity to this company.

What did you do particularly well in identifying their software security vulnerabilities? Why is it important to code securely? What value does software security add to a company’s overall wellbeing?

1. Interpreted clients' needs allowed me to identify potential threats and attacks. Consideration of governmental restrictions was crucial. As per Iron Clas Java, using time-tested libraries provides a good defense and is preferable to a roll your own defense solution.
   Learned how to identify false positives in security reports during static testing phase.

What about the process of working through the vulnerability assessment did you find challenging or helpful?
Determining which parts correlate to the project can be tricky.
In the case of Artemis Financial, we are highlighting security enhancements in API / Secure API interactions as we have focused on creating a secure RESTful api using Spring boot and maven.
We have focused on secure Cryptography / Encryption by adding a getHash that demonstrates our cipher string encrypt/decrypt functionality with AES.
We have focused on securing our Client / Server – By ensuring HTTPS (Hypertext Transfer Protocol Secure) encryption is used between client and server during transfer requests. Additionally by ensuring our self-signed certificate used for HTTP is valid, and the information in the certificate is correct to prevent man-in-the-middle attacks.
Finally, we focused on Encapsulation / Secure Data Structures by utilizing principle of least privilege to our accessor methods, and making our static instance data as private.

How did you approach the need to increase layers of security? What techniques or strategies would you use in the future to assess vulnerabilities and determine mitigation techniques?
I approach the need based on a general security consensus recommendations from Iron clad Java, Java secure coding guidelines, and OWASP recommendations.

How did you ensure the code and software application were functional and secure? After refactoring code, how did you check to see whether you introduced new vulnerabilities?
Regression testing and static analysis comes in handy to ensure refactored code is secure.

What resources, tools, or coding practices did you employ that you might find helpful in future assignments or tasks?
Static analysis testing, certificate generation, Spring boot server setup, secure coding guidelines.

Employers sometimes ask for examples of work that you have successfully completed to demonstrate your skills, knowledge, and experience. What from this particular assignment might you want to showcase to a future employer?

Specifically, implementing an encryption algorithm in a Spring Boot server securely was both a challenging and rewarding experience.
