Para execução do sistema é necessário o uso destes 3 projetos

- https://github.com/Ravenport/gestao-fornecedores-front

- https://github.com/Ravenport/gestao-fornecedores

- https://github.com/Ravenport/gestao-documentos

Após baixar esses projetos também se faz necessário o uso das seguintes tecnologias:

- Minikube
- Imagem Kafka 4.1.1 (Eu usei a do apache)
- Docker

Finalizado a instalação dessas tecnologias é só seguir esse passo a passo, por convêniencia vou incluir
os comandos necessários para subir o kafka da documentação do strimzi.io.

Iniciar o Minikube (A memoria extra é opicional)
- minikube start --memory=4096

Start Strimzi

- minikube kubectl -- create namespace kafka
- minikube kubectl -- create -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka

Starting Kafka

- minikube kubectl -- apply -f https://strimzi.io/examples/latest/kafka/kafka-single-node.yaml -n kafka

Com o Kafka rodando (Subir o Kafka primeiro pois os sistemas dependem dele), é, só ir na raiz dos três projetos e rodar os seguintes arquivos:

Front-End:
- minikube kubectl -- apply -f gestao-fornecedores-front.yaml

Gestao de Fornecedores:
- minikube kubectl -- apply -f gestao-fornecedores.yaml

Gestao de Documentos:
- minikube kubectl -- apply -f gestao-documentos.yaml

Acredito que feito isso o sistema vai estar funcional.
