#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <time.h>  
#include <sys/types.h>
#include <errno.h>
#include <arpa/inet.h>       

#define PORT 5555
#define BUFSIZE 512
#define NUM_CLIENTS 30

typedef struct
{
  int i;
  int user;
  int auth;
  char nickname[10];
  char password[10];
  char role[10];
  int reg;
  char time[100];

} client;


client search_client (client clients[], int i)
{
  for (int index = 0; index < NUM_CLIENTS; index++) {

    if(clients[index].i == i)
    {

      return clients[index];

    }
  }
}

client* update (client clients[], client client)
{
  for (int index = 0; index < NUM_CLIENTS; index++)
  {

    if (clients[index].i == client.i)
    {

      clients[index] = client;
      break;

    }
  }

  return clients;
}

client set_user(client client, char c[])
{
    if (strcmp(c, "NICK") == 0)
    {

      client.user = 1;

    }
    else
    {
      client.user = 0;
    }

    return client;
}

client set_auth(client client, char c[])
{
  if(strcmp(c, "PASS") == 0)
  {
    client.auth = 1;
  }
  else
  {
    client.auth = 0;
  }

  return client;
}

client set_registed(client client, int r)
{
  if(r == 0)
  {
    client.reg = 0;
  }
  else
  {
    client.reg = 1;
  }

  return client;
}

client set_role(client client, int r)
{
  if(r == 0)
  {
    memset(client.role, 0, sizeof(client.role));
    strcpy(client.role, "regs");
  }
  else
  {
    memset(client.role, 0, sizeof(client.role));
    strcpy(client.role, "oper");
  }

  return client;
}

int ascii (char nickname[])
{

  for(int i = 5; i < strlen(nickname) - 1; i++)
  {

    if(nickname[i] > 127 || nickname[i] < 0)
    {

      return 0;
    }
  }

  return 1;
}

int check_nickname (client clients[], char buffer[])
{

  char nickname[10];
  strcpy(nickname, &buffer[5]);
  //nickname[strlen(nickname) - 1] = '\0';
  
  for (int index = 0; index < NUM_CLIENTS; index++)
  {

    if(strcmp(clients[index].nickname, nickname) == 0)
    {

      memset(nickname, 0, sizeof(nickname));
      return 0;

    }
  }

  memset(nickname, 0, sizeof(nickname));
  return 1;

}

int check_spaces (char buffer[])
{

  for(int i = 5; i < strlen(buffer); i++)
  {
    //printf("Char: %c", buffer[i]);
    if (buffer[i] == ' ')
    {

      //printf("%ld\n", strlen(buffer));
      //printf("%c e %d\n", buffer[i], i);
      //printf("teste\n");
      
      return 1;

    }

  }

  //printf("teste1\n");
 
  return 0;

}

int empty (char buffer[])
{

  for(int i = 5; i < strlen(buffer); i++)
  {

    if (buffer[i] != ' ')
    {

      return 0;

    }

  }

  return 1;

}

int authenticate (client client, char buffer[])
{

  FILE *fp;
  char buf_pass[10];
  char line[BUFSIZE];
  char *token;
  const char space[2] = " ";
  char nick[10];
  char pass[10];

  strcpy(buf_pass, &buffer[5]);
  //buf_pass[strlen(buf_pass) - 1] = '\0';

  fp = fopen("server_data.txt", "r");

  if ( fp == NULL )
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {
      
      token = strtok(line, space);
      strcpy(nick, token);
      
      token = strtok(NULL, space);
      strcpy(pass, token);

      //printf("%s = %s\n", nick, client.nickname);
      //printf("%ld = %ld\n", strlen(nick), strlen(client.nickname));
      //printf("%s = %s\n", buf_pass, pass);
      //printf("%ld = %ld\n", strlen(buf_pass), strlen(pass));

      if ((strcmp(client.nickname, nick) == 0) && (strcmp(buf_pass, pass) == 0))
      {

        //printf("true\n");
        fclose(fp);

        return 1;

      }

    }

  }

  fclose(fp);

  return 0;

}

int role(client client, char buffer[])
{
  FILE *fp;
  char buf_pass[10];
  char line[BUFSIZE];
  char *token;
  const char space[2] = " ";
  char nick[10];
  char pass[10];
  char role[10];
  char aux[10];
  

  strcpy(buf_pass, &buffer[5]);
  //buf_pass[strlen(buf_pass) - 1] = '\0';

  fp = fopen("server_data.txt", "r");

  if ( fp == NULL )
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {
      token = strtok(line, space);
      strcpy(nick, token);
      
      token = strtok(NULL, space);
      strcpy(pass, token);

      token = strtok(NULL, space);
      strcpy(role, token);
      strcpy(aux, role);

      //role[strlen(role) - 1] = '\0';
      /* printf("%ld = %ld\n", strlen(role), strlen("regs"));
      printf("%s = %s\n", role, "regs");
      printf("%s = %s\n", client.nickname, nick);
      printf("%s = %s\n", buf_pass, pass); */

      if (strcmp(client.nickname, nick) == 0)
      {
        if(strcmp(role, "regs") == 0)
        {
          //printf("registado\n");
          fclose(fp);
          return 0;
        }
        else
        {
          //printf("operador\n");
          fclose(fp);
          return 1;
        }

      }

      strcpy(role, aux);

    }

  }

  fclose(fp);

  return 0;

}

int check_regs (char buffer[])
{

  FILE *fp;
  char buf_nick[10];
  char line[BUFSIZE];
  char *token;
  const char space[2] = " ";
  char nick[10];

  strcpy(buf_nick, &buffer[5]);
 
  //buf_nick[strlen(buf_nick) - 1] = '\0';

  fp = fopen("server_data.txt", "r");

  if ( fp == NULL )
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {
      
      token = strtok(line, space);
      strcpy(nick, token);
      //printf("%s = %s \n", buf_nick, nick);
      if (strcmp(buf_nick, nick) == 0)
      {
        //printf("ta registado\n");
        fclose(fp);

        return 1;

      }

    }

  }

  fclose(fp);

  return 0;

}

int check_regs2 (char buffer[])
{

  FILE *fp;
  char buf_nick[10];
  char line[BUFSIZE];
  char *token;
  const char space[2] = " ";
  char nick[10];

  strcpy(buf_nick, buffer);
 
  //buf_nick[strlen(buf_nick) - 1] = '\0';

  fp = fopen("server_data.txt", "r");

  if ( fp == NULL )
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {
      
      token = strtok(line, space);
      strcpy(nick, token);
      //printf("%s = %s \n", buf_nick, nick);
      if (strcmp(buf_nick, nick) == 0)
      {
        //printf("ta registado\n");
        fclose(fp);

        return 1;

      }

    }

  }

  fclose(fp);

  return 0;

}


int kick_regs (char buffer[])
{

  FILE *fp, *tmp_fp;
  char buf_nick[10];
  char line[BUFSIZE];
  char save_line[BUFSIZE];
  int num_line = 1;
  char *token;
  const char space[2] = " ";
  char nick[10];

  strcpy(buf_nick, &buffer[5]);
 
  //buf_nick[strlen(buf_nick) - 1] = '\0';

  fp = fopen("server_data.txt", "r");
  tmp_fp = fopen("tmp.txt", "w");

  if (fp == NULL || tmp_fp == NULL)
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {
      //printf("isto e a linha: ");
      //printf("%s\n", line);
      strcpy(save_line, line);
      token = strtok(line, space);
      strcpy(nick, token);
      //printf("%s = %s \n", buf_nick, nick);
      if (strcmp(buf_nick, nick) != 0)
      {
        //printf("isto e a linha: ");
        //printf("%s\n", line);
        // printf("%ld\n", strlen(line));
        // memset(line, 0, sizeof(line));
        // strcpy(line, save_line);
        // fputs("----------------------", fp);
        // fclose(fp);
        fputs(save_line, tmp_fp);
        memset(save_line, 0, sizeof(save_line));
        
      }

    }

  }

  fclose(fp);
  fclose(tmp_fp);

  remove("server_data.txt");
  rename("tmp.txt", "server_data.txt");

  return 1;

}

int regs_user(char buffer[])
{
  FILE *fp, *tmp_fp;
  char buf_nick[20];
  char line[BUFSIZE];
  char save_line[BUFSIZE];
  int num_line = 1;
  char *token;
  char *token2;
  const char space[2] = " ";
  char nick[10];
  char nickname[10];
  char password[10];
  int i = 0, j = 0;

  strcpy(buf_nick, &buffer[5]);

  //buf_nick[strlen(buf_nick) - 1] = '\0';
 
  token2 = strtok(buf_nick, space);
  strcpy(nickname, token2);
  token2 = strtok(NULL, space);
  strcpy(password, token2);

  //printf("NICKNAME: %ld \n PASSWORD: %ld \n", strlen(nickname), strlen(password));
  //printf("NICKNAME: %s \n PASSWORD: %s \n", nickname, password);
  //


  fp = fopen("server_data.txt", "r");
  tmp_fp = fopen("tmp.txt", "w");

  if (fp == NULL || tmp_fp == NULL)
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {
      //printf("isto e a linha: ");
      //printf("%s\n", line);
      strcpy(save_line, line);
      token = strtok(line, space);
      strcpy(nick, token);
      //printf("%s = %s \n", buf_nick, nick);
      if (strcmp(nickname, nick) != 0)
      {
        j++;
      }
      fputs(save_line, tmp_fp);
      memset(save_line, 0, sizeof(save_line));
      i++;
    }

    if(j == i)
    {
      //printf("Ainda n está registado\n\n");
      strcat(nickname, space);
      strcat(nickname, password);
      strcat(nickname, space);
      strcat(nickname, "regs \n");
      //printf("FINAL: %s\n", nickname);
      fputs(nickname, tmp_fp);
    }
    else
    {
      fclose(fp);
      fclose(tmp_fp);
      remove("server_data.txt");
      rename("tmp.txt", "server_data.txt");
      return 0;
    }

  }

  fclose(fp);
  fclose(tmp_fp);

  remove("server_data.txt");
  rename("tmp.txt", "server_data.txt");

  return 1;

}

int oper_user(char buffer[])
{
  FILE *fp, *tmp_fp;
  char buf_nick[10];
  char line[BUFSIZE];
  char save_line[BUFSIZE];
  int num_line = 1;
  char *token;
  const char space[2] = " ";
  char nick[10];
  char pass[10];
  char aux[10];
  char role[10];
  int i= 0;

  strcpy(buf_nick, &buffer[5]);
 
  //buf_nick[strlen(buf_nick) - 1] = '\0';

  fp = fopen("server_data.txt", "r");
  tmp_fp = fopen("tmp.txt", "w");

  if (fp == NULL || tmp_fp == NULL)
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {

      strcpy(save_line, line);

      token = strtok(line, space);
      strcpy(nick, token);
      
      token = strtok(NULL, space);
      strcpy(pass, token);

      token = strtok(NULL, space);
      strcpy(role, token);
      strcpy(aux, role);
      //printf("%ld = %ld\n", strlen(buf_nick), strlen(nick));
      //printf("%s = %s\n", buf_nick, nick);
      if (strcmp(buf_nick, nick) == 0)
      {
        i++;
       strcat(nick, space);
       strcat(nick, pass);
       strcat(nick, space);
       strcat(nick, "oper \n"); 
       strcpy(save_line, nick);
      }
      //printf("FINAL:%s", save_line);
      fputs(save_line, tmp_fp);
      memset(save_line, 0, sizeof(save_line));
    }
    if(i == 0)
    {
      fclose(fp);
      fclose(tmp_fp);
      remove("server_data.txt");
      rename("tmp.txt", "server_data.txt");
      return 0;
    }

  }

  fclose(fp);
  fclose(tmp_fp);

  remove("server_data.txt");
  rename("tmp.txt", "server_data.txt");

  return 1;
}

int quit_oper(client client)
{
  FILE *fp, *tmp_fp;
  char line[BUFSIZE];
  char save_line[BUFSIZE];
  int num_line = 1;
  char *token;
  const char space[2] = " ";
  char nick[10];
  char pass[10];
  char aux[10];
  char role[10];
  //printf("TAMANHO: %ld\n", strlen(client.nickname));
  //printf("NOME: %s\n", client.nickname);
  fp = fopen("server_data.txt", "r");
  tmp_fp = fopen("tmp.txt", "w");

  if (fp == NULL || tmp_fp == NULL)
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {

      strcpy(save_line, line);

      token = strtok(line, space);
      strcpy(nick, token);
      
      token = strtok(NULL, space);
      strcpy(pass, token);

      token = strtok(NULL, space);
      strcpy(role, token);
      strcpy(aux, role);
      //printf("%ld = %ld\n", strlen(client.nickname), strlen(nick));
      //printf("%s = %s\n", client.nickname, nick);
      if (strcmp(client.nickname, nick) == 0)
      {
       strcat(nick, space);
       strcat(nick, pass);
       strcat(nick, space);
       strcat(nick, "regs \n"); 
       strcpy(save_line, nick);
      }
      //printf("FINAL:%s", save_line);
      fputs(save_line, tmp_fp);
      memset(save_line, 0, sizeof(save_line));

    }

  }

  fclose(fp);
  fclose(tmp_fp);

  remove("server_data.txt");
  rename("tmp.txt", "server_data.txt");
  return 1;
}

int change_nick(char buffer[], char new_nick[])
{
  FILE *fp, *tmp_fp;
  char buf_nick[20];
  char line[BUFSIZE];
  char save_line[BUFSIZE];
  int num_line = 1;
  char *token;
  char *token2;
  const char space[2] = " ";
  char nick[10];
  char nickname[10];
  char password[10];
  char role[10];
  int i = 0, j = 0;

  strcpy(buf_nick, buffer);

  //buf_nick[strlen(buf_nick) - 1] = '\0';
 
  token2 = strtok(buf_nick, space);
  strcpy(nickname, token2);

  //printf("NICKNAME: %ld \n PASSWORD: %ld \n", strlen(nickname), strlen(password));
  //printf("NICKNAME: %s \n PASSWORD: %s \n", nickname, password);
  //


  fp = fopen("server_data.txt", "r");
  tmp_fp = fopen("tmp.txt", "w");

  if (fp == NULL || tmp_fp == NULL)
  {

    return 0;

  }

  else
  {
    
    while(fgets(line, BUFSIZE, fp) != NULL)
    {
      //printf("isto e a linha: ");
      printf("%s\n", line);
      strcpy(save_line, line);
      token = strtok(line, space);
      strcpy(nick, token);
      token = strtok(NULL, space);
      strcpy(password, token);
      token = strtok(NULL, space);
      strcpy(role, token);
      printf("%s", role);

      //printf("%s = %s \n", buf_nick, nick);
      if (strcmp(buf_nick, nick) == 0)
      {
        strcat(new_nick, space);
        strcat(new_nick, password);
        strcat(new_nick, space);
        strcat(new_nick, role);
        strcat(new_nick, space);
        strcat(new_nick, "\n");
        fputs(new_nick, tmp_fp);
      }
      else
      {
        fputs(save_line, tmp_fp);
        memset(save_line, 0, sizeof(save_line));
      }
    }

  }

  fclose(fp);
  fclose(tmp_fp);

  remove("server_data.txt");
  rename("tmp.txt", "server_data.txt");

  return 1;

}

int main (int argc, char const *argv[]) 
{
  
  fd_set all_fds;               // master file descriptor list
  fd_set sel_fds;               // temp file descriptor list for select()
  int maxfd;                    // maximum file descriptor number
  int server_fd; 
  struct sockaddr_in address;
  int opt = 1;                  // for setsockopt() SO_REUSEADDR, below
  int addrlen = sizeof(address); 
  char buffer[BUFSIZE];
  int bytes;
  client clients[NUM_CLIENTS];
  client client_tmp;
  char old_nickname[10];
  int inc = 0;
  int client_index;
  int save_client_index;
  char mssg[BUFSIZE];
  char *token;
  char space[2] = " ";
  char username[10];

  for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
  {

    clients[client_index].i = -1;
    clients[client_index].user = 0;
  }

  client_index = 0;

  // Creating socket file descriptor 
  if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
  {
    
    perror("socket failed"); 
    exit(EXIT_FAILURE);

  } 
  
  // Forcefully attaching socket to the port 5555 
  if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt)))
  {
    
    perror("setsockopt failed"); 
    exit(EXIT_FAILURE);

  }

  address.sin_family = AF_INET; 
  address.sin_addr.s_addr = INADDR_ANY; 
  address.sin_port = htons( PORT ); 
  
  // Bind the socket to the network address and port
  if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0)
  {
    
    perror("bind failed"); 
    exit(EXIT_FAILURE);
   
  }

  if (listen(server_fd, 5) < 0)
  {
    
    perror("listen failed"); 
    exit(EXIT_FAILURE);

  }

  FD_ZERO(&all_fds);            // clear the master and temp sets
  FD_SET(server_fd, &all_fds);  // add socket to set of fds
  maxfd = server_fd;            // for now, the max is this one
  
  // Server loop
  while(1)
  {
    
    int i;
    sel_fds = all_fds; // copy set of fds (because select() changes it)
    
    // Wait for data in ONE or MORE sockets
    if (select(maxfd+1, &sel_fds, NULL, NULL, NULL) == -1)
    {
      
      perror("select failed");
      exit(EXIT_FAILURE);

    }

    // If we got here, there's data somewhere...
    // Let's find where

    for (i = 0; i <= maxfd; i++)
    {
      // we got one!!!
      // Now if it's the main socket, a client is connecting
      // If not, a client must have sent data (or disconnected)
      //int c = 0;
      if (FD_ISSET(i, &sel_fds))
      {
        
        if (i == server_fd)
        {
          // A connection is ready to be accept()ed
          if ((i = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen))<0)
          {
            
            perror("accept failed"); 
            exit(EXIT_FAILURE);

          }

          //inc++;
          //printf("%d\n", client_index);
          //printf("%d\n", clients[client_index].i);


          // printf("Client connected.\n");

          // But now we have to monitor this socket too
          FD_SET(i, &all_fds);

          // and update the maxfd, if necessary
          maxfd = i > maxfd ? i : maxfd;

          clients[client_index].i = i;
          //printf("%d\n", clients[client_index].auth);
          clients[client_index].auth = 0;
          clients[client_index].user = 0;
          clients[client_index].reg = 0;
          strcpy(clients[client_index].role, "not_regs");
          //printf("%d\n", clients[client_index].i);
          //printf("%d\n", i);   
          client_index++;
          //printf("%d", client_index);       
        }

        else
        {

          // here, we have data in one client, must recv() it
          bzero(buffer, BUFSIZE);
          
          bytes = recv(i, buffer, BUFSIZE-1, 0);

          if (bytes == 0)
          {
            
            // client disconnected, too bad...
            FD_CLR(i, &all_fds);
            // nevermind the maxfd...

          }

          else
          {
            buffer[strlen(buffer)-1] = '\0';
            
            if (strncmp(buffer, "NICK", 4) != 0 && search_client(clients, i).user == 0)
            {
  
              send(i, "RPLY 202", strlen("RPLY 202"), 0);
              close(i);
              FD_CLR(i, &all_fds);

            }

            else
            {

              if (search_client(clients, i).user == 0)
              {

                if ((strlen(buffer) < 6))
                {

                  send(i, "RPLY 002", strlen("RPLY 002"), 0);
      
                }

                else if (strlen(buffer) > 14 || ascii(buffer) == 0 || check_spaces(buffer) == 1)
                {

                  send(i, "RPLY 003", strlen("RPLY 003"), 0);
           
                }

                else if (check_nickname(clients, buffer) == 0)
                {

                  send(i, "RPLY 004", strlen("RPLY 004"), 0);

                }

                else if (check_regs(buffer) == 0)
                {
                  
                  client_tmp = search_client(clients, i);
                  strcpy(client_tmp.nickname, &buffer[5]);
                  update(clients, client_tmp);
                  printf(" - %s ", client_tmp.nickname);
                  printf("connected to the server");
                  client_tmp = set_user(search_client(clients, i), "NICK");
                  update(clients, client_tmp);
                  client_tmp = set_registed(search_client(clients, i), 0);
                  update(clients, client_tmp);
                  printf(" with role: %s \n", client_tmp.role);

                  time_t rawtime;
                  struct tm * timeinfo;

                  time ( &rawtime );
                  timeinfo = localtime ( &rawtime );
                  printf ( " - Current local time and date: %s", asctime (timeinfo) );
                  strcpy(client_tmp.time, asctime(timeinfo));
                  update(clients, client_tmp);
                  send(i, "RPLY 001", strlen("RPLY 001"), 0);
                  
                  save_client_index = client_index;
                  
                  //strcpy(mssg, "1");
                  strcpy(mssg, "MSSG server:> novo utilizador ");
                  strcat(mssg, client_tmp.nickname);
                  strcat(mssg, "\n");

                  for (client_index = 0; client_index < NUM_CLIENTS; client_index++) 
                  {
                    //printf("%d\n", clients[client_index].i);

                    if ((clients[client_index].i != i) && (clients[client_index].i != -1) && (clients[client_index].user == 1)) {

                      send(clients[client_index].i, mssg, strlen(mssg), 0);
                      
                    }

                  }

                  client_index = save_client_index;

                  memset(mssg, 0, sizeof(mssg));
                  
                }

                else
                {

                  client_tmp = search_client(clients, i);
                  strcpy(client_tmp.nickname, &buffer[5]);
                  update(clients, client_tmp);
                  printf(" - %s ", client_tmp.nickname);
                  printf("connected to the server");

                  if ((role(client_tmp, buffer)) == 0)
                  {
                    
                    client_tmp = set_user(search_client(clients, i), "NICK");
                    update(clients, client_tmp);
                    client_tmp = set_registed(search_client(clients, i), 1);
                    update(clients, client_tmp);
                    client_tmp = set_role(search_client(clients, i), 0);
                    update(clients, client_tmp);
                    //printf("REGISTED: %d \n", client_tmp.reg);
                    printf(" with role: %s \n", client_tmp.role);
                    time_t rawtime;
                    struct tm * timeinfo;

                    time ( &rawtime );
                    timeinfo = localtime ( &rawtime );
                    printf ( " - Current local time and date: %s", asctime (timeinfo) );
                    strcpy(client_tmp.time, asctime(timeinfo));
                    update(clients, client_tmp);
                    send(i, buffer, strlen(buffer), 0);

                  }

                  else {

                    client_tmp = set_user(search_client(clients, i), "NICK");
                    update(clients, client_tmp);
                    client_tmp = set_registed(search_client(clients, i), 1);
                    update(clients, client_tmp);
                    client_tmp = set_role(search_client(clients, i), 1);
                    update(clients, client_tmp);
                    printf("with role: %s \n", client_tmp.role);
                    //printf("REGISTED: %d \n", client_tmp.reg);
                    time_t rawtime;
                    struct tm * timeinfo;

                    time ( &rawtime );
                    timeinfo = localtime ( &rawtime );
                    printf ( " - Current local time and date: %s", asctime (timeinfo) );
                    strcpy(client_tmp.time, asctime(timeinfo));
                    update(clients, client_tmp);
                    send(i, buffer, strlen(buffer), 0);

                  }
                  
                }

              }

              else
              {
                
                if (strncmp(buffer, "NICK", 4) == 0)
                {
                  
                  if ((strlen(buffer) < 6))
                  {
                    
                    send(i, "RPLY 002", strlen("RPLY 002"), 0);

                  }
                  
                  else if (strlen(buffer) > 14 || ascii(buffer) == 0 || check_spaces(buffer) == 1)
                  {
                    
                    send(i, "RPLY 003", strlen("RPLY 003"), 0);

                  }
                  
                  else if (check_nickname(clients, buffer) == 0)
                  {
                    
                    send(i, "RPLY 004", strlen("RPLY 004"), 0);

                  }

                  else if (check_regs(buffer) == 0)
                  {
                    client_tmp = search_client(clients, i);
                    if(check_regs2(client_tmp.nickname) == 0){
                      printf("N TA REGISTADO");
                      strcpy(old_nickname, client_tmp.nickname);
                      memset(client_tmp.nickname, 0, sizeof(client_tmp.nickname));
                      strcpy(client_tmp.nickname, &buffer[5]);
                      //client_tmp.nickname[strlen(client_tmp.nickname) - 1] = '\0';
                      update(clients, client_tmp);
                    
                      printf(" - ");
                      printf("%s ", old_nickname);
                      printf(" mudou o seu nome para");
                      printf(" %s\n", client_tmp.nickname);
                      
                      save_client_index = client_index;

                      //strcpy(mssg, "2");
                      strcpy(mssg, "MSSG server:> ");
                      strcat(mssg, old_nickname);
                      strcat(mssg, " mudou o seu nome para ");
                      strcat(mssg, client_tmp.nickname);
                      strcat(mssg, "\n");
                    }
                    else{
                      printf("TA REGISTADO");
                      strcpy(old_nickname, client_tmp.nickname);
                      strcpy(old_nickname, client_tmp.nickname);
                      memset(client_tmp.nickname, 0, sizeof(client_tmp.nickname));
                      strcpy(client_tmp.nickname, &buffer[5]);

                      change_nick(old_nickname, &buffer[5]);

                      update(clients, client_tmp);
                    
                      printf(" - ");
                      printf("%s ", old_nickname);
                      printf(" mudou o seu nome para");
                      printf(" %s\n", client_tmp.nickname);
                      
                      save_client_index = client_index;

                      //strcpy(mssg, "2");
                      strcpy(mssg, "MSSG server:> ");
                      strcat(mssg, old_nickname);
                      strcat(mssg, " mudou o seu nome para ");
                      strcat(mssg, client_tmp.nickname);
                      strcat(mssg, "\n");

                    }
                    
                    for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
                    {
                      //printf("%d\n", clients[client_index].i);
                      
                      if ((clients[client_index].i != -1) && (clients[client_index].user == 1))
                      {
                        send(clients[client_index].i, mssg, strlen(mssg), 0); 
                
                      }

                    }

                    client_index = save_client_index;

                    memset(mssg, 0, sizeof(mssg));
                    memset(old_nickname, 0, sizeof(old_nickname));

                  }

                  else
                  {
                    send(i, "RPLY 004", strlen("RPLY 004"), 0);

                  }

                }
                
                else
                {
                  
                  if (strncmp(buffer, "MSSG", 4) == 0)
                  {
                    
                    //printf("%ld\n", strlen(buffer));
                      
                    if ((strlen(buffer) < 6) || buffer[5] == ' ' || empty(buffer) == 1)
                    {
                      
                      send(i, "RPLY 102", strlen("RPLY 102"), 0);

                    }

                    else if (strlen(buffer) > BUFSIZE)
                    {
                        
                      send(i, "RPLY 103", strlen("RPLY 103"), 0);

                    }

                    else
                    {
                      
                      //printf("%ld\n", strlen(buffer));
                      send(i, "RPLY 101", strlen("RPLY 101"), 0);

                      client_tmp = search_client(clients, i);

                      //strcpy(mssg, "3");
                      strcpy(mssg, "MSSG ");
                      strcat(mssg, client_tmp.nickname);
                      strcat(mssg, ":> ");
                      strcat(mssg, &buffer[5]);

                      save_client_index = client_index;
                      
                      for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
                      {
                        
                        //printf("%d\n", clients[client_index].i);
          
                        if ((clients[client_index].i != i) && (clients[client_index].i != -1) && (clients[client_index].user == 1))
                        {
                      
                          send(clients[client_index].i, mssg, strlen(mssg), 0);
                
                        }

                      }
                      
                      client_index = save_client_index;

                      memset(mssg, 0, sizeof(mssg));

                    }

                  }

                  else if ((strncmp(buffer, "PASS", 4) == 0) && (search_client(clients, i).auth == 0))
                  {

                    client_tmp = search_client(clients, i);
                   
                    //printf("%d\n", result);

                    if ((strlen(buffer) < 6) || strlen(buffer) > 14 || check_spaces(buffer) == 1 || authenticate(client_tmp, buffer) == 0)
                    {
                      
                      send(i, "RPLY 203", strlen("RPLY 203"), 0);

                    }

                    else
                    {

                      //Se o resultado de role() for 0 cliente é registado
                      if ((role(client_tmp, buffer)) == 0)
                      {

                        client_tmp = search_client(clients, i);
                        strcpy(client_tmp.password, &buffer[5]);
                        update(clients, client_tmp);
                        client_tmp = set_auth(search_client(clients, i), "PASS");
                        update(clients, client_tmp);
                        send(i, "RPLY 201", strlen("RPLY 201"), 0);

                      }

                      //senão é operador
                      else
                      {

                        client_tmp = search_client(clients, i);
                        strcpy(client_tmp.password, &buffer[5]);
                        update(clients, client_tmp);
                        client_tmp = set_auth(search_client(clients, i), "PASS");
                        update(clients, client_tmp);
                        send(i, "RPLY 201", strlen("RPLY 201"), 0);

                      }
                    
                    }

                  }

                  //está registado (meteu a pass)
                  else if (search_client(clients, i).auth == 1)
                  {

                    if ((strncmp(buffer, "WHOS", 4)) == 0)
                    {

                      save_client_index = client_index;

                      strcpy(mssg, "server:> RPLY 501 ");

                      for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
                      {

                        if (clients[client_index].user == 1)
                        {

                          char t[100];
                          strcpy(t, clients[client_index].time);
                          t[strlen(t)-1] = '\0';
                          strcat(mssg, clients[client_index].nickname);
                          strcat(mssg, "/");
                          strcat(mssg, clients[client_index].role);
                          strcat(mssg, "/");
                          strcat(mssg, t);
                          strcat(mssg, ";  ");

                        }

                      }

                      client_index = save_client_index;

                      send(i, mssg, strlen(mssg), 0);

                    }

                    else if((strncmp(buffer, "LIST", 4)) == 0)
                    {

                      send(i, "RPLY 401",strlen("RPLY 401"), 0);

                    }

                    //Não é operador mas é registado
                    else if (strcmp(search_client(clients, i).role, "regs") == 0)
                    {
                      

                      if((strncmp(buffer, "KICK", 4)) == 0)
                      {

                        send (i, "RPLY 602", strlen("RPLY 602"), 0);

                      }

                      else if((strncmp(buffer, "REGS", 4)) == 0)
                      {

                        send(i, "RPLY 702", strlen("RPLY 702"), 0);

                      }

                      else if ((strncmp(buffer, "OPER", 4)) == 0)
                      {

                        send(i, "RPLY 802", strlen("RPLY 802"), 0);

                      }

                      else if ((strncmp(buffer, "QUIT", 4)) == 0)
                      {

                        send(i, "RPLY 902", strlen("RPLY 902"), 0);

                      }

                      else
                      {

                        send(i, buffer, strlen(buffer), 0);

                      }

                    }

                    //É operador
                    else
                    {

                      if ((strncmp(buffer, "KICK", 4)) == 0)
                      {

                        if (kick_regs(buffer) == 1)
                        {

                          send(i, "RPLY 601", strlen("RPLY 601"), 0);

                          //strcpy(mssg, "4");
                          strcpy(mssg, "MSSG server:> ");
                          strcat(mssg, &buffer[5]);
                          strcat(mssg, " foi expulso");

                          save_client_index = client_index;
                      
                          for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
                          {
                            
                            //printf("%d\n", clients[client_index].i);
          
                            if ((clients[client_index].i != i) && (clients[client_index].i != -1) && (clients[client_index].user == 1))
                            {

                              send(clients[client_index].i, mssg, strlen(mssg), 0);
                              
                            }

                          }
                      
                          client_index = save_client_index;

                          memset(mssg, 0, sizeof(mssg));

                          char *token;
                          char space[2] = " ";
                          char comand[10];
                          char nick[10];
                          token = strtok(buffer, space);
                          strcpy(comand, token);
                          
                          token = strtok(NULL, space);
                          strcpy(nick, token);

                          //caso o cliente o registado esteja connectado ao servidor
                          for (int n = 0; n < client_index; n++)
                          {

                            //printf("%d", client_index);
                            //printf("\n%ld = %ld\n", strlen(nick), strlen(clients[n].nickname));
                            //printf("\n%s = %s\n", nick, clients[n].nickname);

                            if (strcmp(clients[n].nickname, nick) == 0)
                            {

                              strcpy(clients[n].role, "not_regs");
                              clients[n].reg = 0;
                              clients[n].auth = 0;

                            }

                          }

                        }
                        
                        else
                        {

                          send(i, buffer, strlen(buffer), 0);

                        }
                        
                      }
                      else if ((strncmp(buffer, "REGS", 4)) == 0)
                      {

                        if (regs_user(buffer) == 1)
                        {
                          
                          send(i, "RPLY 701", strlen("RPLY 701"), 0);

                          //strcpy(mssg, "5");
                          token = strtok(&buffer[5], space);
                          strcpy(username, token);
                          strcpy(mssg, "MSSG server:> ");
                          strcat(mssg, username);
                          strcat(mssg, " foi registado");

                          save_client_index = client_index;
                      
                          for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
                          {
                            
                            //printf("%d\n", clients[client_index].i);
          
                            if ((clients[client_index].i != i) && (clients[client_index].i != -1) && (clients[client_index].user == 1))
                            {
                              
                              send(clients[client_index].i, mssg, strlen(mssg), 0);

                            }

                          }
                      
                          client_index = save_client_index;

                          memset(mssg, 0, sizeof(mssg));

                          char *token;
                          char space[2] = " ";
                          char comand[10];
                          char nick[10];
                          token = strtok(buffer, space);
                          strcpy(comand, token);
                          
                          token = strtok(NULL, space);
                          strcpy(nick, token);

                          //caso o cliente o registado esteja connectado ao servidor
                          for (int n = 0; n < client_index; n++)
                          {

                            //printf("%d", client_index);
                            //printf("\n%ld = %ld\n", strlen(nick), strlen(clients[n].nickname));
                            //printf("\n%s = %s\n", nick, clients[n].nickname);

                            if (strcmp(clients[n].nickname, nick) == 0)
                            {

                              strcpy(clients[n].role, "regs");
                              clients[n].reg = 1;

                            }

                          }

                        }

                        else
                        {

                          send(i, buffer, strlen(buffer), 0);

                        }

                      }

                      else if ((strncmp(buffer, "OPER", 4)) == 0)
                      {

                        if (oper_user(buffer) == 1)
                        {

                          send(i, "RPLY 801", strlen("RPLY 801"), 0);

                          //strcpy(mssg, "6");
                          strcpy(mssg, "MSSG server:> ");
                          strcat(mssg, &buffer[5]);
                          strcat(mssg, " foi promovido a operador");

                          save_client_index = client_index;
                      
                          for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
                          {
                            
                            //printf("%d\n", clients[client_index].i);
          
                            if ((clients[client_index].i != i) && (clients[client_index].i != -1) && (clients[client_index].user == 1))
                            {

                              send(clients[client_index].i, mssg, strlen(mssg), 0);
                              
                            }

                          }
                      
                          client_index = save_client_index;

                          memset(mssg, 0, sizeof(mssg));

                          char *token;
                          char space[2] = " ";
                          char comand[10];
                          char nick[10];
                          token = strtok(buffer, space);
                          strcpy(comand, token);
                          
                          token = strtok(NULL, space);
                          strcpy(nick, token);

                          //caso o cliente o registado esteja connectado ao servidor
                          for (int n = 0; n < client_index; n++)
                          {

                            //printf("%d", client_index);
                            //printf("\n%ld = %ld\n", strlen(nick), strlen(clients[n].nickname));
                            //printf("\n%s = %s\n", nick, clients[n].nickname);

                            if (strcmp(clients[n].nickname, nick) == 0)
                            {

                              strcpy(clients[n].role, "oper");

                            }

                          }

                        }

                        else
                        {

                          send(i, buffer, strlen(buffer), 0);

                        }
            
                      }

                      else if ((strncmp(buffer, "QUIT", 4)) == 0)
                      {

                        if (quit_oper(search_client(clients, i)) == 1)
                        {

                          client_tmp = search_client(clients, i);
                          strcpy(client_tmp.role, "regs");
                          update(clients, client_tmp);
                          //printf("NOVO admin: %s %s\n\n", client_tmp.nickname, client_tmp.role); 
                          send(i, "RPLY 901", strlen("RPLY 901"), 0);

                          //strcpy(mssg, "7");
                          strcpy(mssg, "MSSG server:> ");
                          strcat(mssg, client_tmp.nickname);
                          strcat(mssg, " deixou de ser operador");

                          save_client_index = client_index;
                      
                          for (client_index = 0; client_index < NUM_CLIENTS; client_index++)
                          {
                            
                            //printf("%d\n", clients[client_index].i);
          
                            if ((clients[client_index].i != i) && (clients[client_index].i != -1) && (clients[client_index].user == 1))
                            {
                              
                              send(clients[client_index].i, mssg, strlen(mssg), 0);

                            }

                          }
                      
                          client_index = save_client_index;

                          memset(mssg, 0, sizeof(mssg));

                        }

                        else
                        {

                          send(i, buffer, strlen(buffer), 0);

                        }

                      }

                      else
                      {

                        send(i, buffer, strlen(buffer), 0);

                      }

                    }

                  }

                  else if((strncmp(buffer, "OPER", 4) == 0))
                  {

                    //Se não estiver autenticado e é registado
                    if((search_client(clients, i).auth == 0) && (search_client(clients, i).reg == 1))
                    {
                      
                      send(i, "RPLY 803", strlen("RPLY 803"), 0);

                    }

                    else
                    {
                      client_tmp = search_client(clients, i);
                      strcpy(mssg, "server:> RPLY 804 - Erro: Ação não autorizada, utilizador ");
                      strcat(mssg, client_tmp.nickname);
                      strcat(mssg, " não é um utilizador registado");
                      send(i, mssg, strlen(mssg), 0);

                    }

                  }

                  else
                  {
                    
                    send(i, buffer, strlen(buffer), 0);
                      
                  }
                  
                }

              }

            }

            if (strncmp(buffer, "EXIT", 4) == 0)
            {

              // client wants to quit, let it
              client_tmp = set_user(search_client(clients, i), "EXIT");

              printf(" - %s ", client_tmp.nickname);
              printf("disconnected from the server\n");
              //printf("%d\n", client_tmp.i);
              //printf("%d\n", i);

              memset(client_tmp.nickname, 0, sizeof(client_tmp.nickname));
              memset(client_tmp.nickname, 0, sizeof(client_tmp.password));
              memset(client_tmp.role, 0, sizeof(client_tmp.role));
              client_tmp.auth = 0;
              client_tmp.user = 0;
              client_tmp.reg = 0;
              update(clients, client_tmp);
              close(i);
              FD_CLR(i, &all_fds);

            }

          }

        }

      }

    }

  }
  
  return 0;
  
}