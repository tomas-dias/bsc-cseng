#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <wchar.h>
#include <wctype.h>
#include <locale.h>
#include <time.h>
#include "../include/hashsep.h"
#include "../include/list.h"
#include "../include/t9lib.h"


int main(int argc, char *argv[])
{
    setlocale( LC_ALL, "" );
    
    if( argc > 1 )
    {
        struct timespec start, stop;
        double accum;
        HashTable H;
        ElementType Keyboard[NUM_DIGITS];

        //wprintf( L"%ld\n", CountWords( argv[1] ) );
        H = InitializeTable( CountWords( argv[1] ) );
        //wprintf( L"%ld\n", H->TableSize );

        BuildKeyboard( Keyboard );

        clock_gettime( CLOCK_REALTIME, &start );
        LoadDictionary( argv[1], H, Keyboard );
        clock_gettime( CLOCK_REALTIME, &stop );

        accum = ( stop.tv_sec - start.tv_sec ) + ( stop.tv_nsec - start.tv_nsec ) / ( double ) BILLION;
        wprintf( L"\nDuração do carregamento do dicionário: %lf\n", accum );
        wprintf( L"\n" );
        //PrintHashTable( H );

        int i;
        long int code, encoded;
        bool first = true;
        bool invalid = false;
        wchar_t input[BUFFER_SIZE];
        wchar_t message[BUFFER_SIZE];

        List L;
        Position P;
        ElementType E;

        message[0] = L'\0';
        
        wprintf( L"** Escreva a sua mensagem **\n" );

        while(1)
        {
            wprintf( L"\n** Teclado **\n" );
            wprintf( L"2: a b c á à â ã ç\n3: d e f é ê\n4: g h i í\n5: j k l\n6: m n o ó ô õ\n7: p q r s\n8: t u v ú\n9: w x y z\n" );
            wprintf( L"\nInserir: " );
            wscanf( L"%ls", input );
            
            for( i = 0; i < wcslen( input ); i++ )
            {
                if( !iswdigit( input[i] ) || ( ( input[i] == L'0' || input[i] == L'1' ) && wcslen( input ) > 1 ) )
                {
                    wprintf( L"\nERRO: Input inválido!\n" );
                    invalid = true;
                    break;
                }
            }

            if( invalid )
            {
                invalid = false;
                continue;
            }

            code = wcstol( input, NULL, 10 );

            if( code == 1 )
            {
                if( message[0] == L'\0' )
                    wprintf( L"\nA mensagem está vazia!" );
                else
                {
                    wprintf( L"Mensagem: %ls", message );

                    message[0] = L'\0';
                    first = true;
                }

                wprintf( L"\n\n** Escreva a sua mensagem **\n" );
            }
            else if( code == 0 )
            {
                wprintf( L"Deseja sair da aplicação (s/n)? " );
                wscanf( L"%ls", input );

                if( wcscmp( input, L"s" ) == 0 )
                    break;
                
                else if( wcscmp( input, L"n" ) != 0 )
                    wprintf( L"\nERRO: Opção inválida!\n" );
            }
            else
            {
                L = RetrieveList( code, H );
                P = Header( L );
                
                while( P->Next != NULL )
                {
                    E = Retrieve( P->Next );
                    
                    if( E->digits == code )
                    {
                        wprintf( L"Sugestão: %ls, aceita (s/n)? ", E->letters );
                        wscanf( L"%ls", input );
                        
                        if( wcscmp( input, L"s" ) == 0 )
                        {
                            if( first )
                            {
                                wcscpy( message, E->letters );
                                wcscat( message, L" " );
                                first = false;
                            }
                            else
                            {
                                wcscat( message, E->letters );
                                wcscat( message, L" " );
                            }

                            break;
                        }
                        else if( wcscmp( input, L"n" ) != 0 )
                            wprintf( L"\nERRO: Opção inválida!\n" );
                        else
                            P = Advance( P );
                    }
                    else
                        P = Advance( P );
                }
                if( P->Next == NULL )
                {
                    wprintf( L"Não existem mais sugestões; introduza a palavra do teclado.\n" );
                    wscanf( L"%ls", input );

                    for( i = 0; i < wcslen( input ); i++ )
                    {
                        if( iswdigit( input[i] ) )
                        {
                            wprintf( L"\nERRO: A palavra só pode conter letras!\n" );
                            invalid = true;
                            break;
                        }
                    }

                    if( invalid )
                    {
                        invalid = false;
                        continue;
                    }

                    encoded = Encode( input, Keyboard );

                    if( encoded != code )
                    {
                        wprintf( L"\nERRO: O código e a palavra introduzidos não coincidem!\n" );
                    }
                    else
                    {
                        E = malloc( sizeof( ElementType ) );
                        E->digits = code;
                        E->letters = malloc( ( wcslen( input ) + 1 ) * sizeof( wchar_t ) );
                        wcscpy( E->letters, input );

                        if( Find( E, H ) != NULL )
                        {
                            wprintf( L"\nERRO: A palavra introduzida já existe!\n" );
                            free( E->letters );
                            free( E );
                        }
                        else
                        {
                            Insert(E, H);

                            if( first )
                            {
                                wcscpy( message, input );
                                wcscat( message, L" " );
                                first = false;
                            }
                            else
                            {
                                wcscat( message, input );
                                wcscat( message, L" " );
                            }
                        }
                    }
                }
            }
        }
    }
    else
        wprintf( L"ERRO: Dicionário não introduzido.\n" );
}
