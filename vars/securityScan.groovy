def call(imageName, imageTag) {

    echo "Running Trivy Security Scan"

    sh """
    trivy image \
      --format template \
      --template @security/html.tpl \
      -o trivy-report.html \
      ${imageName}:${imageTag}
    """

}