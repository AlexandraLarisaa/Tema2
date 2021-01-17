<h1>Aplicație Meteo :sunny:</h1> 

## Descriere Aplicație :thought_balloon:
  Aplicația implementează un sistem meteo prin care este detaliată starea vremii in diverse orașe ale lumii. Pentru obținerea informațiilor despre vreme s-a folosit OpenWeather Api si un fișier de intrare cu diferite date despre țări si orașe. Informații precum temperatura, presiunea, umiditatea, starea vremii etc. sunt transmise din API.  Parsarea raspunsului este realizata cu ajutorul bibliotecii json-simple-1.1.1 si este in format JSON.
 
  
  
## Uilizare Aplicație :question:
Să presupunem că doriți să solicitați informații meteo folosind numele țării, atunci ar trebui să utilizați:

<code> URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + chb_city.getValue() + "&appid=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect(); </code>
            
            
 Se selectează țara si orașul ale căror detalii meteo dorim să le aflăm. Aplicația va afișa informațiile dorite, imbrăcate intr-un design corespunzător si plăcut ochiului. 
 
 ## Dependințe Aplicație :lock_with_ink_pen:
 
 Aplicatia WeatherApp foloseste urmatoarele librarii si framework-uri:

- [JUnit-4.13.1][1]
- [Json-simple-1.1.1][2]
- [mockito][3]
- [hamcrest-core-1.3][4]

[1]: https://search.maven.org/search?q=g:junit%20AND%20a:junit
[2]: https://code.google.com/archive/p/json-simple/downloads
[3]: https://github.com/mockito/mockito
[4]: https://search.maven.org/artifact/org.hamcrest/hamcrest-core/1.3/jar
 
