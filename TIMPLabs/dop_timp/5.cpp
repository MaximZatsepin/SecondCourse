/*
 Задача 5.20
  Функция с параметрами строка1 и строка2 добавляет в конец строки1 каждый ее символ, совпадающий
 с каким-либо символом строки2 и возвращает либо модифицированную строку, либо, если не было
 добавлено ни одного символа, NULL.
 
 Приложение из введенной строки-фразы формирует два массива слов: исходный и новый массив, для
 получения каждого нового слова этого массива берут два подряд идущих слова из исходной строки-фразы
 и обрабатывают их по правилу выше описанной функции.

 */
#include <iostream>

using namespace std;

char* poisrWord(char* str, int& len);
char* phrase_input();
int dlina(char* str);
char** createArrayOfWords(char* str, int& countOfWords);
char* createNewWord(char* word1, char* word2);
char** calculation(char** arr, int count);

void output_array(char** array, int count);


int len = 0;
int countOfArr = 0;
//int countOfArr2 = 0;


int main(){
    setlocale(LC_ALL, "rus");
    cout << "Введите первую строку: ";
    char* string1 = phrase_input();
    
    cout << endl <<  "Введенная строка: " << string1 << endl;
    
    char** arrayOfWords = createArrayOfWords(string1, countOfArr);
    
    cout << endl << "Исходный массив: | ";
    output_array(arrayOfWords, countOfArr);
    cout <<endl << "Его длина: " << countOfArr << endl;
    
    char** newArrayOfWords = calculation(arrayOfWords, countOfArr);
    
    cout << endl << endl <<  "Новый массив: | ";
    output_array(newArrayOfWords, countOfArr/2);
    
    
}

int dlina(char* str) {
    int dlina = 0;
    while (*str) {
        dlina++;
        str++;
    }
    return dlina;
}

char* poisrWord(char* str, int& len){
    len = 0;
    char* word;
    
    // Пока пробел двигаю
    while(*str == ' ') str++;
    if(*str == '\0') return 0;
    // Запоминаю указатель на первое слово
    word = str;
    
    // Считаю длину
    while(*word != ' ' && *word != '\0') { word++; len++; }
        
    return str;
}

char* phrase_input(){
    char* phrase = new char[100];
    cin.get(phrase,100,'\n');
    cin.ignore();
    return phrase;
}

char** createArrayOfWords(char* str, int& countOfWords){
    char** array = new char*[20];

    for(countOfWords = 0; *str; countOfWords++){
        char* word = poisrWord(str, len);
        array[countOfWords] = new char[len];
        //word[len] = '\0';  // Обрубаю слово
        //for(int i = 0; i < len; i++) - без нее все работает
        //array[countOfWords] = word;
        for(int i = 0; i < len; i++,word++){
            array[countOfWords][i] = *word;
        }
        str = word + 1; // Двигаю указатель на следующее слово
    }
    return array;
}





char** calculation(char** arr, int count){
    
    char** newArr = new char*[20];
    
    for(int i = 0,j=0; i < count-1; i+=2,j++){
        char* slovo1 = arr[i];
        char* slovo2 = arr[i+1];
        newArr[j] = createNewWord(slovo1, slovo2);
    }
    
    return newArr;
}


char* createNewWord(char* word1, char* word2){

    int dlina1 = dlina(word1);
    int dlina2 = dlina(word2);
    char* newWord = new char[dlina1+dlina2];

    // Занес первое слово
    for(int i = 0; i < dlina1; i++){
        newWord[i] = word1[i];
    }

    for(int i = 0,k=0 ; i < dlina1; i++){
        //char symbol = word1[i];
        for(int j = 0 ; j < dlina2; j++){
            // Если совпало, заношу символ
            if(word1[i] == word2[j]){
                newWord[dlina1+k] = word1[i];
                k++;
                break;
            }
        }
    }

    return newWord;
}

/*

char* createNewWord(char* word1, char* word2){
    
    int dlina1 = dlina(word1);
    int dlina2 = dlina(word2);
    char* newWord = new char[dlina1+dlina2];
    
    char* start = word1; // Создаю двигающийся указатель и отправляю в него слово1
    char* slovo = newWord; // Создаю двигающийся указатель и отправляю в него новое слово
    
    // Занес первое слово
    for(int i = 0; i < dlina1; i++,start++,slovo++){
        *slovo = *start;
    }
    
    char *start1 = word1; // Создаю двигающийся указатель и отправляю в него слово1
    while(*start1){  // иду циклом до конца слова1
        char *start2 = word2; // Создаю двигающийся указатель и отправляю в него слово2
        char symbol1 = *start1; // беру символ с первого слова
        while(*start2){ // иду циклом до конца слова2
            char symbol2 = *start2; // беру символ со второго слова
            if(symbol2 == symbol1){ // делаю проверку символов
                *slovo = symbol1;
                slovo++; // записываю символ в указатель нового слова и двигаю указатель
                break;
            }
            start2++;
        }
        start1++;
        
    }

*/
    
//    return newWord; // возвращаю новое слово
//}


void output_array(char** array, int count){
    for (int i = 0; i < count; i++){
        cout << array[i] << " | ";
    }
}