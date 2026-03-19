# Jenkins Shared Library – CI/CD Pipeline Library

This repository contains an **Jenkins Shared Library** used to standardize CI/CD pipelines across multiple applications.

The library provides reusable pipeline stages for java springboot application:

- Application Build
- Container Image Build using Kaniko
- Container Security Scanning using Trivy
- Kubernetes Deployment using Helm

It follows **best practices used in large organizations** to avoid duplication of pipeline code and enforce standardized DevOps workflows.

---

# 1. What is Jenkins Shared Library?

A **Jenkins Shared Library** is a mechanism in Jenkins that allows teams to create **reusable pipeline code** which can be shared across multiple Jenkins pipelines.

Instead of writing the same pipeline logic repeatedly in multiple repositories, teams place the reusable pipeline logic inside a shared library repository.

Jenkins pipelines then **import and execute functions from this shared library**.

This helps organizations maintain:

- Consistency
- Reusability
- Centralized pipeline logic
- Easier maintenance

The shared library works with:

- Jenkins Declarative Pipelines  
- Jenkins Scripted Pipelines  

---

# 2. Why Use Jenkins Shared Libraries?

Without shared libraries, every repository contains its own pipeline logic.

Example problem:
- repo-1 - Jenkinsfile
- repo-2 - Jenkinsfile
- repo-3 - Jenkinsfile


Each Jenkinsfile contains similar steps like:

- Build
- Docker image creation
- Security scan
- Deployment

This creates problems:

- Code duplication
- Hard to maintain pipelines
- Difficult to enforce security policies
- Updates must be done in many repositories

Shared libraries solve this by centralizing pipeline logic.

- Jenkins
  - Shared Library
    - Build logic
    - Docker image build logic
    - Security Scan logic
    - Deployment logic


All pipelines reuse the same functions.

Benefits:

- Standardized CI/CD pipelines
- Faster onboarding for new services
- Easier maintenance
- Centralized DevOps governance

---

# 3. Where Jenkins Shared Libraries Are Used

Shared libraries are widely used in **enterprise DevOps environments** where many microservices share the same CI/CD workflow.

Typical scenarios include:

### Microservices Platforms

When an organization runs **50+ microservices**, each repository can reuse the same pipeline stages.

Example workflow:

Git Push --> Jenkins Pipeline --> Build --> Docker image build --> Security Scan --> Deployment


Shared libraries ensure all services follow the same pipeline standards.

---

### Platform Engineering

Platform teams maintain a central shared library so that:

- Developers do not write pipeline logic
- CI/CD standards remain consistent
- Security scans are always executed

---

### DevOps Governance

Shared libraries help enforce:

- Mandatory security scanning
- Container image standards
- Deployment patterns

---

# 4. Repository Structure

- jenkins-shared-library
  - vars
    - buildApp.groovy
    - buildImage.groovy
    - securityScan.groovy
    - helmDeploy.groovy
  - src
  - resources
  - README.md


---

## Jenkins Shared Library Directory Explanation

### vars/

The `vars` directory contains **pipeline steps that Jenkins pipelines can call directly**.

Example:
- buildApp()
- securityScan()
- helmDeploy()

Each file defines a reusable pipeline stage.

---

### src/

The `src` directory contains **Groovy utility classes** organized in packages.

These classes help organize complex logic and allow modular pipeline design.

Example:
- PipelineUtils
- GitUtils
- HelmUtils


---

### resources/

The `resources` directory contains **static files used during pipeline execution**, such as:

- Kubernetes pod templates
- Shell scripts
- Helm configuration templates

These files can be loaded dynamically during pipeline execution.

---

# 5. Pipeline Stages Provided by This Library

This shared library currently provides the following pipeline stages.

---

## 1. Application Build Stage

This stage compiles the application using Maven.

Typical tasks include:

- Compile source code
- Package the application
- Generate build artifacts


---

## 2. Container Image Build Stage

This stage builds container images using:

Kaniko allows building Docker images **inside Kubernetes without requiring Docker daemon access**, making it suitable for secure containerized CI environments.

The image is tagged dynamically using the pipeline build number and pushed to docker repository.


---

## 3. Security Scan Stage

This stage performs container vulnerability scanning using:
Trivy scans container images for:

- OS vulnerabilities
- Application dependencies
- Security misconfigurations

Security scanning helps detect vulnerabilities before deployment.

---

## 4. Helm Deployment Stage

This stage deploys the application to Kubernetes using:
Helm simplifies Kubernetes deployments by packaging Kubernetes manifests into reusable charts.

Deployment includes:

- Dynamic image tag injection
- Release upgrades



---
# 6. Enterprise Best Practices Implemented

This shared library follows enterprise DevOps practices:

- Centralized pipeline logic
- Dynamic container image tagging
- Security scanning before deployment
- Kubernetes-native CI/CD
- Reusable pipeline stages
- Modular pipeline architecture
---
# 7 Configuring Shared Jenkins Library

To use a shared Jenkins library (`devops-shared-lib`) in your pipelines, you must configure it in **Manage Jenkins**.

---

## Steps in Jenkins UI

1. **Open Jenkins Dashboard**
   - Log in to your Jenkins instance.
   - Click on **Manage Jenkins**.

2. **Go to Configure System**
   - In the Manage Jenkins menu, select **Configure System**.

3. **Locate Global Pipeline Libraries**
   - Scroll down to the section **Global Pipeline Libraries**.
   - Click **Add** to create a new library entry.

4. **Fill in Library Details**
   - **Name**: `devops-shared-lib`  
     (This must match the name used in your Jenkinsfile: `@Library('devops-shared-lib') _`)
   - **Default Version**: `main` (or whichever branch you want Jenkins to use by default).
   - **Retrieval Method**: Git.
   - **Project Repository**: Enter the GitHub repo URL of your shared library.
   - **Credentials**: Add credentials if the repo is private.

5. **Save Configuration**
   - Click **Save** at the bottom of the page.

---
# 8 Next Step 

This  repository will demonstrate how to call this shared library from a Jenkins pipeline - https://github.com/sanjeevk021995/jenkins-shared-library-springboot.git
