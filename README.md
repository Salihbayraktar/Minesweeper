<h1>Turkish</h1>

Uygulama ilk başta bizden oyun alanının satır sayısını istiyor. Bu değeri integer olarak giriyoruz.

Daha sonra ise sütun sayısını istiyor bu sayıyı da integer olarak giriyoruz.

En son olarak ise oyun alanında bulunmasını istediğimiz mayın sayısını integer cinsinden girerek oyunu başlatıyoruz.

Oyun başladıktan sonra aralarında boşluk olacak şekilde ilk başta işlem yapmak istediğimiz satırın değerini, ikinci olarak sütunun ve son olarakta hangi modda işlem yapmak istediğimiz belirtiyoruz.

Oyun üzerinde 2 adet mod bulunuyor. (free ve mine)

free modundayken mayın tarlası üzerinde girdiğimiz değerleri açıyoruz. Üzerinde mayın olan değer açılırsa oyun bitiyor.

mine modundayken ise seçtiğimiz bölgeyi yıldızlayarak o bölgede mayın olduğunu belirtiyoruz. 

Örnek Girdiler.

3 7 free : 3. satır 7. sütundaki değeri gösterir.

8 15 free : 8. satır 15. sütundaki değeri gösterir.

15 6 mine : 15. satır 6. sütundaki değerin işaretini '*' yapar ve mayın tahmini olarak kaydeder.

Programın örnek çıktısı.
<pre>
Enter the number of rows of the playground
16
Enter the number of columns of the playground
16
How many mines do you want on the field?
25
  |0000000001111111|
  |1234567890123456|
  |----------------|
 1|................|
 2|................|
 3|................|
 4|................|
 5|................|
 6|................|
 7|................|
 8|................|
 9|................|
10|................|
11|................|
12|................|
13|................|
14|................|
15|................|
16|................|
Set/unset mines marks or claim a cell as free:
3 7 free
  |0000000001111111|
  |1234567890123456|
  |----------------|
 1|................|
 2|................|
 3|......3.........|
 4|................|
 5|................|
 6|................|
 7|................|
 8|................|
 9|................|
10|................|
11|................|
12|................|
13|................|
14|................|
15|................|
16|................|
Set/unset mines marks or claim a cell as free:
8 15 free
  |0000000001111111|
  |1234567890123456|
  |----------------|
 1|................|
 2|................|
 3|......3.........|
 4|................|
 5|................|
 6|................|
 7|................|
 8|..............1.|
 9|................|
10|................|
11|................|
12|................|
13|................|
14|................|
15|................|
16|................|
Set/unset mines marks or claim a cell as free:
1 3 free
  |0000000001111111|
  |1234567890123456|
  |----------------|
 1|////1...........|
 2|////1...........|
 3|////123.........|
 4|111//1..........|
 5|..1/12..........|
 6|111/2...........|
 7|////2...........|
 8|////1.........1.|
 9|//111...........|
10|//1.112.........|
11|//111/1.211.....|
12|11////111/1.....|
13|.1////////12....|
14|11//111////1....|
15|////1.211112....|
16|////1...........|
Set/unset mines marks or claim a cell as free:
15 6 mine
  |0000000001111111|
  |1234567890123456|
  |----------------|
 1|////1...........|
 2|////1...........|
 3|////123.........|
 4|111//1..........|
 5|..1/12..........|
 6|111/2...........|
 7|////2...........|
 8|////1.........1.|
 9|//111...........|
10|//1.112.........|
11|//111/1.211.....|
12|11////111/1.....|
13|.1////////12....|
14|11//111////1....|
15|////1*211112....|
16|////1...........|
Set/unset mines marks or claim a cell as free:
13 1 free
  |0000000001111111|
  |1234567890123456|
  |----------------|
 1|////1...X.......|
 2|////1X..X.......|
 3|////123.........|
 4|111//1XX........|
 5|.X1/12.......X..|
 6|111/2X.X......X.|
 7|////2X..........|
 8|////1.........1.|
 9|//111.X.X.....X.|
10|//1X112.X.......|
11|//111/1X211....X|
12|11////111/1X....|
13|X1////////12....|
14|11//111////1X..X|
15|////1X211112....|
16|////1..X..X.....|
You stepped on a mine and failed!

Process finished with exit code 0
</pre>

