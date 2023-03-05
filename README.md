# APP-APACHE-AVRO-JAVA

### :pushpin: Features

- [x] Envio de Strings como mensagens.
- [x] Envio de mensagens com o Apache Avro.
- [x] Validação de schemas com Schema Registry.

### :hammer: Pré-requisitos

Será necessário a utilização de uma IDE de sua preferência e do JDK 17.

### 🎲 Iniciando projeto pela primeira vez

```bash
# Clone este repositório
git clone https://github.com/leonardodantas/app-apache-avro-java.git

# Tenha o docker compose instalando, acesse a pasta raiz do projeto e execute o seguinte comando
docker-compose up

# O comando acima ira criar instâncias das seguintes aplicações
- Kafka
- Schema Registry

# Inicie a aplicação com uma IDE de sua preferência

#Acesse o seguinte endereço no navegador
http://localhost:8080/swagger-ui/index.html

```

### 🛠 Detalhes Tecnicos

- Java 17
- Records
- Eventos assincronos.
- Apache Avro.
- Schema Registry.
- Mensageria com Kakfa.
- Docker.

## Documentação da API

- ### Enviar mensagens como String.

```
POST /send/product/object/mapper
```

CURL de exemplo:

```
curl -X 'POST' \
  'http://localhost:8080/send/product/object/mapper' \
  -H 'accept: */*' \
  -d ''
```

Log gerado:

```
2023-03-04 18:11:36.660  INFO 4156 --- [io-8080-exec-10] c.a.j.controllers.SendProductController  : -----------------------------------------
2023-03-04 18:11:36.664  INFO 4156 --- [io-8080-exec-10] c.a.j.controllers.SendProductController  : Initialized send product with object mapper
2023-03-04 18:11:36.665  INFO 4156 --- [io-8080-exec-10] c.a.java.utils.GeneratorRandom           : Generate Product with random values
2023-03-04 18:11:36.672  INFO 4156 --- [io-8080-exec-10] c.a.j.k.p.ProductObjectMapperProducer    : Create ProduceRecord:
2023-03-04 18:11:36.673  INFO 4156 --- [io-8080-exec-10] c.a.j.k.p.ProductObjectMapperProducer    : TOPIC: sendProductObjectMapper
2023-03-04 18:11:36.673  INFO 4156 --- [io-8080-exec-10] c.a.j.k.p.ProductObjectMapperProducer    : KEY: 57d8fc1f-03cb-40a7-9e9a-f95f575c1248
2023-03-04 18:11:36.759  INFO 4156 --- [ad | producer-1] c.a.j.k.p.ProductObjectMapperProducer    : Send with Object Mapper
2023-03-04 18:11:36.760  INFO 4156 --- [ad | producer-1] c.a.j.k.p.ProductObjectMapperProducer    : OFFSET: 5, PARTITION: 0
2023-03-04 18:11:36.760  INFO 4156 --- [ad | producer-1] c.a.j.k.p.ProductObjectMapperProducer    : Serialized Value Size = 371
2023-03-04 18:11:36.767  INFO 4156 --- [ntainer#1-0-C-1] c.a.j.k.c.ProductObjectMapperConsumer    : Received message with key 57d8fc1f-03cb-40a7-9e9a-f95f575c1248
2023-03-04 18:11:36.768  INFO 4156 --- [ntainer#1-0-C-1] c.a.j.k.c.ProductObjectMapperConsumer    : {id='de4561cf-8853-4340-820a-72429d4b8162', code='57d8fc1f-03cb-40a7-9e9a-f95f575c1248', name='NtzznglvwhJRgfekuwhS', price=-1026688196}
2023-03-04 18:11:36.768  INFO 4156 --- [ntainer#1-0-C-1] c.a.j.k.c.ProductObjectMapperConsumer    : -----------------------------------------```
```

- ### Envia mensagem com Apache Avro.

```
POST /send/product/avro
```

CURL de exemplo:

```
curl -X 'POST' \
  'http://localhost:8080/send/product/avro' \
  -H 'accept: */*' \
  -d ''
```

Log gerado:

```
2023-03-04 18:13:50.421  INFO 4156 --- [nio-8080-exec-4] c.a.j.controllers.SendProductController  : -----------------------------------------
2023-03-04 18:13:50.426  INFO 4156 --- [nio-8080-exec-4] c.a.j.controllers.SendProductController  : Initialized send product with apache avro
2023-03-04 18:13:50.426  INFO 4156 --- [nio-8080-exec-4] c.a.java.utils.GeneratorRandom           : Generate Product with random values
2023-03-04 18:13:50.432  INFO 4156 --- [nio-8080-exec-4] c.a.j.k.producers.ProductAvroProducer    : Create ProduceRecord:
2023-03-04 18:13:50.437  INFO 4156 --- [nio-8080-exec-4] c.a.j.k.producers.ProductAvroProducer    : TOPIC: sendProductAvro
2023-03-04 18:13:50.438  INFO 4156 --- [nio-8080-exec-4] c.a.j.k.producers.ProductAvroProducer    : KEY: c3d1eccc-7109-460a-ae88-d139938ce130
2023-03-04 18:13:50.451  INFO 4156 --- [ad | producer-2] c.a.j.k.producers.ProductAvroProducer    : Send with Apache Avro
2023-03-04 18:13:50.451  INFO 4156 --- [ad | producer-2] c.a.j.k.producers.ProductAvroProducer    : OFFSET: 6, PARTITION: 0
2023-03-04 18:13:50.451  INFO 4156 --- [ad | producer-2] c.a.j.k.producers.ProductAvroProducer    : Serialized Value Size = 165
2023-03-04 18:13:50.457  INFO 4156 --- [ntainer#0-0-C-1] c.a.j.k.consumers.ProductAvroConsumer    : Received message with key c3d1eccc-7109-460a-ae88-d139938ce130
2023-03-04 18:13:50.458  INFO 4156 --- [ntainer#0-0-C-1] c.a.j.k.consumers.ProductAvroConsumer    : {id='8d7fcc0f-df51-4052-a56c-5e334b2b5aa8', code='paAGDcPYRIyHhPhtUyLq', name='paAGDcPYRIyHhPhtUyLq', price=-125}
2023-03-04 18:13:50.459  INFO 4156 --- [ntainer#0-0-C-1] c.a.j.k.consumers.ProductAvroConsumer    : -----------------------------------------
```

## Objetivo do desenvolvimento

<p>
Pequena aplicação de estudos, para envio e consumo de mensagens com Apache Avro e Schema Registry. Durante o projeto também realizei a implementação do envio de mensagens do modo convencional, utilizando apenas Strings, para assim realizar uma breve comparação entre os dois meios.
</p>

## Configurações de Beans

<p>
Tendo como objetivo enviar mensagens por dois meios diferentes, realizei a configuração do beans de forma manual, sendo dois beans produtores, uma utilizando String e outro utilizando objetos Avro e dois beans consumidores seguindo os mesmos padrões.
</p>

<p>
A configuração manual de produtores e consumidores pode ser bem útil quando necessitamos de enviar ou consumir mensagens de mais de um servidor kafka diferente.
</p>


```
   @Bean("kafkaTemplateAvro")
    public KafkaTemplate<String, ProductSchema> kafkaTemplateAvro() {
        final var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistry);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(properties));
    }
```

```
   @Bean("kafkaTemplateObjectMapper")
    public KafkaTemplate<String, String> kafkaTemplateObjectMapper() {
        final var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(properties));
    }
```

<p>A principal diferença entre os dois pode ser encontrada na definição do KafkaTemplate e no tipo de serialização do objeto a ser produzido, um com KafkaAvroSerializer e outro com JsonSerializer.</p>

<p>Quando utilizamos o Apache Avro para produzir mensagens, em comparação a Strings normais, temos mensagens de tamanhos menores. Apesar desse enorme benefício que pode melhorar a performance de nossas aplicações, a configuração de produtores Kafka com o Apache Avro exige mais do desenvolvedor.</p>


## Schema Registry

<p>
Schema Registry é uma ferramenta utilizada para o versionamento e para garantir que o produtor e o consumidor irão ser compatíveis. Em nosso Docker Compose é criado uma instância do Schema Registry que pode ser acessado através do Postman ou Insomnia, disponibilizando endpoints para criação de novos schemas, edição, novas versões e exclusão.
</p>

<p>
Com Apache Avro e Schema Registry nossos Schemas sempre serão validados ao publicar a mensagem no consumidor e ao consumir, para isso configuramos e nosso bean produtor e em nosso bean consumidor o endereço de nosso Schema Registry. Toda vez que produzimos ou consumimos uma mensagem, a nossa aplicação realiza uma chamada rest no Schema Registry e valida o nosso schema, para garantir uma boa performance ele faz uso de cache por baixo dos panos.
</p>


## Tecnologias

<div style="display: inline_block">
  <img align="center" alt="java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="kafka" src="https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka" />
  <img align="center" alt="swagger" src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" />
</div>

### :sunglasses: Autor

Criado por Leonardo Rodrigues Dantas.

[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/leonardo-rodrigues-dantas/)](https://www.linkedin.com/in/leonardo-rodrigues-dantas/)
[![Gmail Badge](https://img.shields.io/badge/-leonardordnt1317@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardordnt1317@gmail.com)](mailto:leonardordnt1317@gmail.com)

## Licença

Este projeto está sob a licença MIT.


