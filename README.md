# Building a throw-away testing infrastructure

The majority of QA people I talk to start out with creating and running test automation against an on-premise testing infrastructure. A lot of times they also need to build and maintain it themselves.

A good testing environment is the foundation upon which digital products are built and tested. However, it is a time-consuming process to set up and then to maintain it to stay relevant and useful.

Join me while I highlight the considerations and steps undertaken to create such an environment using Docker, Selenium and GitHub and the challenges encountered during this endeavour. In the end we will abandon this infrastructure and outsource it in favour of Sauce Labs Hosted Orchestration so that DevOps and QA can refocus their efforts and make everyone's life easier.

## Table of Contents

| Links                                                                                      | Notes                                                                                                              |
|--------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| [Hosted Test Orchestration](https://docs.saucelabs.com/hosted-orchestration/)              | Link to the Sauce Labs Hosted Test Orchestration documentation                                                     |
| [Test example](src/test/java/com/saucelabs/ExampleTest.java)                               | Simple test script                                                                                                 |
| [Docker compose for Selenium Grid](docker/docker-compose-v3.yml)                           | Example Docker compose file to setup a Selenium grid                                                               |
| [GitHub action for running Selenium grid and tests](.github/workflows/SeleniumGrid.yml)    | Example GitHub action workflow to run a Selenium grid and execute the tests on it                                  |
| [Hosted Test Orchestration GitHub workflow](.github/workflows/HostedTestOrchestration.yml) | GitHub workflow showing the whole process of creating a docker container with the tests and then running it hosted |
| [Hosted Test Orchestration config](.sauce/config.yml)                                      | Config file for Hosted Test Orchestration                                                                          |