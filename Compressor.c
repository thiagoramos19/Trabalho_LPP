#include <stdio.h>
#include <string.h>

#define TAMANHO 500

void comprimir(char entrada[], char saida[]);
void descomprimir(char entrada[], char saida[]);

void comprimir(char entrada[], char saida[])
{
    int tam = strlen(entrada);
    int i = 0;
    int j = 0;

    while (i < tam) {
        char c = entrada[i];
        int conta = 0;

        while (i < tam && entrada[i] == c && conta < 9) {
            conta++;
            i++;
        }

        saida[j]     = '0' + conta;
        saida[j + 1] = c;
        j = j + 2;
    }

    saida[j] = '\0';
}

void descomprimir(char entrada[], char saida[])
{
    int tam = strlen(entrada);
    int i = 0;
    int j = 0;

    while (i < tam) {
        int conta = entrada[i] - '0';
        char c    = entrada[i + 1];
        i = i + 2;

        int k;
        for (k = 0; k < conta; k++) {
            saida[j] = c;
            j++;
        }
    }

    saida[j] = '\0';
}

int main()
{
    char original[TAMANHO];
    char comprimido[TAMANHO];
    char reconstruido[TAMANHO];

    printf("===========================================\n");
    printf("   COMPRESSOR RLE - Paradigma Imperativo  \n");
    printf("===========================================\n");
    printf("Digite o texto: ");
    scanf("%s", original);

    comprimir(original, comprimido);
    descomprimir(comprimido, reconstruido);

    if (strcmp(original, reconstruido) == 0)
        printf("\n[OK] Texto reconstruido corretamente.\n");
    else
        printf("\n[ERRO] Falha na reconstrucao.\n");

    int tam_orig = strlen(original);
    int tam_comp = strlen(comprimido);
    float reducao = 100.0 * (1.0 - (float)tam_comp / tam_orig);

    printf("\n--- Relatorio de Compressao ---\n");
    printf("Original           : %s\n", original);
    printf("Comprimido         : %s\n", comprimido);
    printf("Tamanho original   : %d bytes\n", tam_orig);
    printf("Tamanho comprimido : %d bytes\n", tam_comp);
    if (reducao > 0)
        printf("Reducao            : %.1f %%\n", reducao);
    else
        printf("Reducao            : sem ganho\n");

    return 0;
}
