FROM registry.access.redhat.com/ubi8/ubi-minimal:8.3
RUN microdnf update && microdnf install nmap && microdnf clean all
RUN mkdir /app

WORKDIR /app

COPY target/*-runner /app/application
COPY entrypoint.sh .
RUN sed -e 's/\r$//' -i entrypoint.sh && chmod +x entrypoint.sh
#RUN chmod +x application

EXPOSE 8080

CMD ["./entrypoint.sh"]
