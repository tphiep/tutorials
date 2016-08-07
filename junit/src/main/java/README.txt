'How to Mock Final Classes in Unit Tests' -  By Neil Buesing
From : https://objectpartners.com/2012/03/15/how-to-mock-final-classes-in-unit-tests/

Mocking has become a key component to writing effective unit-tests. It is a very useful strategy to write focused tests. Mocking frameworks make it easy to write test, but they have their limitations. Both Mockito and EasyMock cannot mock classes (or methods) that are final. This article will show you how to mock such classes while continuing to use your favorite mocking framework (Mockito for the sake of this article).

In a future article, I will extend this strategy to writing unit tests for Google Web Toolkit (GWT) without the need of the GWTTestCase.

Goal

Provide a framework that can mock final classes, while using an existing mocking framework. For this, I have implemented PreMock.

Alternate Solutions

    Refactor – Refactoring the code you need to mock (so it can be mocked) can be the best solution. Extracting an interface, making a method not final, etc. are all valid options. However, it is not always practical, the class you need to mock could be within a 3rd party library. Also, rewriting a class to make it easier to test, may expose more to your API than desired. Furthermore, I am not a fan of adding boiler plate wrapping classes, just to support unit testing (if it can be avoided). The less code you need to write, the easier it is to maintain.
    Don’t Mock – Mocking isn’t always necessary, and sometimes it is not the best choice. For example, instead of mocking your persistence tier for testing your services, provide a persistence tier that can be used within unit tests. See Unit Testing Your Persistence Tier Code.
    Powermock – There is already an open-source library out there that provides a way to mock final classes. I had difficultlies getting it configured for the tests I needed. Also, it could not be extended to create the desired tests for GWT (this is something I plan on covering in a future article).

Strategy

Alter the class, with byte code manipulation, as it is being loaded by a custom class loader. During loading of the class remove the final modifier. When the class is loaded to Mockito it will be a non-final class.

Difficulties

    Get the class loader in place so that any class referenced in the unit test will use by the class loader; allow for our unit-test to be written like any other unit test.
    Alter the byte code of the java class when it is being loaded.
    Make sure that classes that should not be modified, are not modified.

Technologies

    Java 6 – I’m sure Java 5 and Java 7 would work, provided the versions of JUnit, Mockito, and Javassist also worked with the desired version of Java.
    JUnit 4.10 – Junit has gone over some major tranformations in regards to the test runner. Mockito, Spring, and others frameworks provide test runners for making writing unit tests easier. Here we will write a test runner than will allow us to mock those classes and methods that Mockito and Easymock cannot test. Versions since 4.8.2 should work, and I expect versions since 4.7 will also work. The runner changed between version 4.6 and 4.7, so I believe 4.6 and earlier versions of JUnit will not work.
    Mockito 1.9.0 – I am sure this can be applied to older versions of Mockito and other versions of Easymock. While I have used EasyMock longer, I find Mockito easier to use. Mockito has its own JUnit test runner; it will be incorporated into the test runner described here, since I don’t want to loose it’s functionality. I have also tested against version 1.8.5
    Javassist 3.15 – Bytecode manipulation is the key component to removing the final modifier. I’m sure ASM would also work, but personally, I like Javassist. I did some testing against an older version of 3.4, and it worked fine.
