# struts1-json-framework
基於struts1</br>
1.CommonAction</br>
   CommonAction類別繼承原Action類別後實作execute方法並在execute方法內加上錯誤處理與回傳json處理</br>
   因CommonAction實作ICommonAction介面並將doProcess的方法設為抽象方法，</br>
   但繼承CommonAction的類別必須實作doProcess的方法</br>
2.CommonException</br>
   拋出的錯誤類別若為CommonException類別的子類別的建構子會取出Enum中的錯誤代碼與錯誤訊息後拋出錯誤</br>
   若為未定義的錯誤則會拋出rc:9999,rm:系統未定義的錯誤</br>
3.CommonResponse</br>
   CommonResponse中包含rc,rm,data的屬性，其中data的屬性類別為T，T為在繼承CommonAction的類別時必須傳入的類別</br>
   在finally中execute方法會將T類別取出放入回傳的data中。</br>
4.Enum</br>
   Enum中包含了錯誤訊息與錯誤代碼</br>
