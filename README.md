# gRPC-Example

- ProtocolBuffersMapping
- APIDefinition
    - UnaryAPI
      ```
      Client                      Server
        |          request           |
        |        --------->>>        |
        |                            |
        |          response          |
        |        <<<---------        |
      ```
    - ServerStreamingAPI
      ```
      Client                      Server
        |          request           |
        |        --------->>>        |
        |                            |
        |          response (1)      |
        |        <<<---------        | 
        |                            |
        |          response (n)      |
        |        <<<---------        |
      ```
    - ClientStreamingAPI
      ``` 
      Client                      Server
        |          request (1)       |
        |        --------->>>        |
        |                            |
        |          request (n)       |
        |        --------->>>        | 
        |                            |
        |          response          |
        |        <<<---------        |
      ```
    - BidirectionalStreamingAPI
      ``` 
       Client                      Server
       |          request (1)       |
       |        --------->>>        |
       |                            |
       |          response (1)      |
       |        <<<---------        | 
       |                            |
       |          request (2)       |
       |        --------->>>        |
       |                            | 
       |          request (n)       |
       |        --------->>>        |
       |                            |
       |          response (2)      |
       |        <<<---------        |
       |                            |
       |          response (n)      |
       |        <<<---------        |
       ```
- APIErrorHandler
- Deadline
- ClientInterceptor
- ServerInterceptor
- Metadata
- CallCredentials
- Context
- MetadataErrorHandler
- SslTls
- TestingWithMockingStab
- SpringbootIntegrationWith
  ```
  Spring boot starter with gRPC created by @yidongnan

  https://github.com/yidongnan/grpc-spring-boot-starter
  ``` 


- SpringbootRestVsGrpc
  ```
  Steps to run performance comparison between REST and gRPC:
  
  Run servers:
  SpringbootRestVsGrpc/SpringbootRestServer/src/main/java/pl/pb/grpcexample/springbootrestserver/ServerRestApplication.java
  SpringbootRestVsGrpc/SpringbootGrpcServer/src/main/java/pl/pb/grpcexample/springbootgrpcserver/ServerGrpcApplication.java
  
  Run client:
  SpringbootRestVsGrpc/SpringbootClient/src/main/java/pl/pb/grpcexample/springbootclient/ClientApplication.java
  
  Then you should see in console:
  
  Texts was processed with REST communication in: 3186 ms
  Texts was processed with gRPC communication in: 1068 ms
  gRPC WIN ! ! !
  
  Texts was processed with REST communication in: 2671 ms
  Texts was processed with gRPC communication in: 266 ms
  gRPC WIN ! ! !
  
  Texts was processed with REST communication in: 2433 ms
  Texts was processed with gRPC communication in: 242 ms
  gRPC WIN ! ! !
  ...
  ```