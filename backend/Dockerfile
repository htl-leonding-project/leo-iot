FROM ubuntu:impish-20211102
RUN apt update -y && apt install nmap -y
RUN mkdir /app

WORKDIR /app

COPY target/*-runner /app/application
COPY entrypoint.sh .
RUN sed -e 's/\r$//' -i entrypoint.sh && chmod +x entrypoint.sh
#RUN chmod +x application

EXPOSE 8080

CMD ["./entrypoint.sh"]
