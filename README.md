# Producer-Consumer_with_CompletableFuture
I have tried to implement the producer consumer problem using the CompletableFuture class. <br />
My goal was to use the runAsync method, provided by the class CompletableFuture. 
I managed this objective only partially, i.e. in the case with an infinity production but only one call of consuming, and viceversa. <br />
Anyway the solution of the complete problem works creating manually two threads, and inside them nesting other threads created with the CompletableFuture class. 
