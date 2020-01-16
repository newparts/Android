Nume proiect: AplicatieThread

Autor: Iuliu Bakcsi

Descriere: 
Executarea de operatii de rulare indelungata pe firul principal al aplicatiei poate provoca inghetarea interfetei aplicatiei in cateva secunde fortandu-ne sa inchidem aplicatia. Pentru a evita acest lucru trebuie sa mutam operatiile de acest tip pe un thread separat.

In Android pentru a realiza acest demers avem la indemana mai multe modalitati de executie. Fie cream o clasa care extinde Thread si inlocuieste metoda rulata, fie implementam o interfata Runnable apoi implementam interfata la un nou obiect Thread.

Aceste clase Java de bază construiesc bazele pentru clase precum AsyncTask, HandlerThread și ThreadPoolExecutor.

O alta varianta de trimitere a codului din thread de fundal inapoi la threadul UI se poate printr-un handler care trimite un runnable la MessageQueue din threadul UI. 

Pentru a asocia Handlerul cu Looperul threadului principal il punem la dispozitie pe firul principal sau il transmitem pe Looper.getMainLooper la constructor. In loc sa cream un handler, putem folosi, de asemenea, metoda View classes post sau metoda runOnUiThread, care utilizeaza handler-ul principal in interiorul aplicatiei.

Tehnologii folosite (inclusiv biblioteci externe):
Android Studio, Java

Cum se compileaza / ruleaza: 
AplicatieThread ruleaza un Thread in background setat la secunda. Pentru a testa functionalitatea aplicatiei am setat schimbarea aspectului unui buton dupa 5 secunde. Pentru verificarea rularii aplicatiei in timp ce este activ threadul am folosit un buton switch.

