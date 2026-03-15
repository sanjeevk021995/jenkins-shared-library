def call(imageName, imageTag) {

    echo "Running Trivy Security Scan"

    sh """
    trivy image \
      --format template \
      --template @resources/html.tpl \
      -o trivy-report.html \
      ${imageName}:${imageTag}
    """

}
