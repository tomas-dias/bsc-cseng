#include <arpa/inet.h>
#include <errno.h>
#include <netdb.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

#define PORT 5555
#define BUFSIZE 512 // max number of bytes we can get at once 


int main ()
{
	int client_fd, numbytes;
    struct sockaddr_in address;
	char buffer[BUFSIZE];
    int buffer_index;

    // Creating socket file descriptor 
    if ((client_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    { 

        perror("socket failed"); 
        exit(EXIT_FAILURE); 

    } 
    
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY; 
    address.sin_port = htons( PORT );

    if (connect(client_fd, (struct sockaddr *)&address, sizeof(address)) != 0)
    { 
        
        printf("connection with the server failed...\n\n"); 
        exit(0); 

    }
    else
    {
       
        printf("connecting to the server...\n\n");

    }
    
    while (1)
    {
        bzero(buffer, sizeof(buffer));

        printf("> ");

		buffer_index = 0;
        
		while ((buffer[buffer_index++] = getchar()) != '\n');

		send(client_fd, buffer, strlen(buffer), 0);
        
        if ((strncmp(buffer, "EXIT", 4)) == 0)
        { 

            printf("server:> Client Exit...\n\n"); 
			break; 
            
		}
        
		bzero(buffer, sizeof(buffer)); 
		read(client_fd, buffer, sizeof(buffer));

        if ((strcmp(buffer, "RPLY 202")) == 0)
        { 
			
            printf("server:> RPLY 202 - Erro: Nome não está definido\n\n");
			break; 
            
		}

        else if ((strcmp(buffer, "RPLY 001")) == 0)
        {

            printf("server:> RPLY 001 - Nome atribuido com sucesso\n\n");

        }

        else if ((strcmp(buffer, "RPLY 002")) == 0)
        {

            printf("server:> RPLY 002 - Erro: Falta introducao do nome\n\n");

        }

        else if ((strcmp(buffer, "RPLY 003")) == 0)
        {

            printf("server:> RPLY 003 - Erro: Nome pedido não valido\n\n");
  
        }

        else if ((strcmp(buffer, "RPLY 004")) == 0)
        {

            printf("server:> RPLY 004 - Erro: Nome ja em uso\n\n");
    
        }

        else if ((strcmp(buffer, "RPLY 102")) == 0)
        {

            printf("server:> RPLY 102 - Erro: Não ha texto na mensagem\n\n");

        }

        else if ((strcmp(buffer, "RPLY 103")) == 0)
        {

            printf("server:> RPLY 103 - Erro: Mensagem demasiado longa\n\n");

        }

        else if ((strcmp(buffer, "RPLY 101")) == 0)
        {

            printf("server:> RPLY 101 - Mensagem enviada com sucesso\n\n");

        }

        else if ((strcmp(buffer, "RPLY 201")) == 0)
        {
            
            printf("server:> RPLY 201 - Autenticacão com sucesso\n\n");

        }

        else if ((strcmp(buffer, "RPLY 203")) == 0)
        {

            printf("server:> RPLY 203 - Erro: Password incorreta\n\n");

        }
        else if ((strcmp(buffer, "RPLY 401")) == 0)
        {
            printf("RPLY 401  default; cn; oss\n\n");
        }
        else if ((strncmp(buffer, "server:> RPLY 501 ", 18)) == 0)
        {

            printf("%s\n\n", buffer);

        }

        else if ((strcmp(buffer, "RPLY 601")) == 0)
        {

            printf("server:> RPLY 601 - Utilizador expulso\n\n");

        }

        else if ((strcmp(buffer, "RPLY 602")) == 0)
        {

            printf("server:> RPLY 602 - Erro: Ação não autorizada, utilizador cliente não e um operador\n\n");

        }

        else if ((strcmp(buffer, "RPLY 701")) == 0)
        {

            printf("server:> RPLY 701 - Utilizador registado com sucesso\n\n");

        }

        else if ((strcmp(buffer, "RPLY 702")) == 0)
        {

            printf("server:> RPLY 702 - Erro: Ação não autorizada, utilizador cliente não e um operador\n\n");

        }

        else if ((strcmp(buffer, "RPLY 801")) == 0)
        {

            printf("server:> RPLY 801 - Foi promovido a operador\n\n");

        }

        else if ((strcmp(buffer, "RPLY 802")) == 0)
        {

            printf("server:> RPLY 802 - Erro: Ação não autorizada, utilizador cliente não e um operador\n\n");

        }

        else if ((strcmp(buffer, "RPLY 803")) == 0)
        {

            printf("server:> RPLY 803 - Erro: Ação não autorizada, utilizador cliente não está autenticado\n\n");

        }

        else if ((strcmp(buffer, "RPLY 804")) == 0)
        {

            printf("server:> RPLY 804 - Erro: Ação não autorizada, utilizador <nome> não e um utilizador registado\n\n");

        }

        else if ((strcmp(buffer, "RPLY 901")) == 0)
        {

            printf("server:> RPLY 901 - Deixou de ser operador\n\n");

        }

        else if ((strcmp(buffer, "RPLY 902")) == 0)
        {

            printf("server:> RPLY 902 - Erro: Ação não autorizada, utilizador cliente não e um operador\n\n");

        }

        else if ((strncmp(buffer, "MSSG", 4)) == 0)
        {

            printf("%s\n\n", buffer);

        }
        else if ((strncmp(buffer, "server", 6)) == 0)
        {
            printf("%s\n\n", buffer);
        }
        else {

            printf("\n\n");

        }
        
	} 

    close(client_fd);
    
	return 0;
}
