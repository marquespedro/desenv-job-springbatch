# desenv-job-springbatch

Desenvolvimento Job Com Spring Batch 


- Spring batch Componentes
    
    * Job, uma tarefa que será executada
    
    * Step, uma etapa do Job, ou seja um Job pode ser composto por varios Steps 
    
    * JobRepository, repositorio onde é guardado o status de toda execução do job.
      Por exemplo em qual step esta o job em um dado momento, se houve sucesso ou falha em algum step, basicamente
      são metadados do spring batch salva no para controlar todo o fluxo de execução do job.
    
    * JobLauncher, responsável por rodar o job, ele pode ser executado por um serviço rest, por linha de comando, 
      manual, por um agendamento, existem várias formas de executar um job.  
    
    * Job é uma maquina de estados.

-  JOB 
  
    * Todas vez que um Job é executado o spring tem uma instância desse job e ele consegue saber por exemplo quantas vezes aquele job foi executado. 
  
    * é possivel configurar para que rode uma vez ou sempre.
  
    * por default executa somente uma vez e salva os metados no banco, ao tentar rodar novamente ele sempre ira olhar os metadados no banco. 
      somente a fins de teste e possivel passar um 	incrementer(new RunIdIncrementer()) para criar um novo contexto do mesmo job. 

-  Step 
   
    * Possui sua logica de controle para saber se um step rodou com sucesso para uma determinada instancia do job.
   
    * Tasklet, tarefa pequena, limpar um direto, carregar informacoes em cache executado
   
    * Chunk, uma fatia de um grande processamento afim de chegar em um processamento total.
   
    * Item Reader, Item Processor, Item Writer.

- Mais sobre um Chunk :

    * Chunk é utilizado para um processamento de uma tarefa complexa. 
    
    * Chunk é um pedaço de um processamento, por exemplo se temos uma lista de 1000 registros para serem processados 
        um chunk poderia ser definido com 100 registros e teriamos 10 chunks.
    
    * ItemReader, quem irá ler os 100 chunks de cada vez. 
    
    * ItemProcessor,quem irá aplicar uma determinada regra de negocio para cada item lido do chunk, 
        a ideia do ItemProcessor é aplicar a logica item a item e nao a toda lista ele foi modelado para este sentido. 
         
    * ItemWriter, ao final da leitura de todo o chunk e te todo seus itens processados 
        presume que agora o itemWriter possa ser chamado para escrever esses dados. 

- Mais sobre um Tasklet :
    
    * Tasklet, é apenas uma tarefa pequena, carregamento de informacoes em cache, limpar um diretorio, criar diretorio. 


 - Spring Batch Recuperação de Falhas
   
    * Imagine o seguinte cenario onde uma folha de pagamento esta sendo processada e por alguma falha na rede o processamento e interrompido 
      nao seria legal recomecar a processar toda a folha novamente, o ideal seria retormar de onde parou. 
   
    * O spring batch permite a retomada de onde parou, ou seja do ultimo chunk que foi processado com sucesso, pois essas informacoes 
    sao salvas no JobRepository.
   
    * E possivel configuar um skip de exececoes, por exemplo um dado que nao veio no formato esperado ser skipado para um posterior processamento.


 - Spring Batch Multhreading 
   
    * E possivel paralelizar a execucao dos chunk desde que nao haja conflito, por exemplo que o processamento de uma parte do chunk dependa de outra. 
   
    * E possivel paralelizar a execucao dos steps desde nao haja dependencia entre os steps que estejam rodando em paralelo. 
   
    * E possivel fazer uma paralelizacao a nivel de 1 pc e varios cores, multi pc com step master e slaves com message broker 

- Spring Batch Metricas 
    
    * Isso ajuda a acompanhar a execucao do job , que sao salvas no JobRepository
    
    * E possivel ter metricas customizadas com o spring actuactor, como uso de processador, memoria etc. 



