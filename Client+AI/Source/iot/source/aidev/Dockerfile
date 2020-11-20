FROM tensorflow/tensorflow:2.0.1-gpu-py3

ADD ./requirements.txt /requirements.txt

RUN apt-get update

RUN apt-get -y install git

RUN pip3 install --upgrade pip

RUN pip3 install -r /requirements.txt

RUN pip3 install jupyterlab
RUN pip3 install -q git+https://github.com/tensorflow/docs

ENV TINI_VERSION v0.6.0
ADD https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini /usr/bin/tini
RUN chmod +x /usr/bin/tini
ENTRYPOINT ["/usr/bin/tini", "--"]

WORKDIR /iotai

CMD ["jupyter", "lab", "--ip=0.0.0.0", "--port=8888", "--notebook-dir=/iotai", "--allow-root", "--no-browser", "--LabApp.base_url=/aidev/", "--LabApp.password=sha1:07b47c0b470e:5ad3e29bb0252a6154ec897933f482bd4ad9fc3b"]

EXPOSE 8888
