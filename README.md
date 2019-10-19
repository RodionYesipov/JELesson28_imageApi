Суть задачи
==============
*Сделать отдельный репозиторий на github и сделать к нему подробное описание README.md с форматом API  и примерами.*
 
 
 *Сделать 3 запроса:*
 
 *1) отправить картинку на сервер в JSON:*
 
   *описание картинки, например "пейзаж"*
   *сама картинка в формате base64*
 
 *Сервер должен вернуть ID картинки и сохранить в базу. В оперативной памяти не храним.*
 
 *2) получить список всех картинок в формате json, без самих картинок, только описание и id*
 
 *3) получить картинку по id по прямой ссылке*
 
### 1) POST запрос
Данные записываются в таблицу с именем `yesipov` 
на сервер `jdbc:postgresql://164.68.101.149:12347/postgres`
в схему `public`.

***Структура таблицы***

Название поля   | тип данных    |           
----------------|---------------|
id              | int8(19)      |
descript        | varchar(255)  |
imagebase64     | text          |

#### Пример 
##### Request(Java OK HTTP)
```java
OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
RequestBody body = RequestBody.create(mediaType, "imageBase64=%2F9j%2F4AAQSkZJRgABAQAAAQABAAD%2F2wCEAAkGBxMSEhUSExMVFhUXFhcYFRgXFxcYGBgZGBUXFxUYFRcYHSggGBolHRUXITEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGhAQGCsdHR0tLS0tLS0tLSsrLS0tLS0tLS0tLS0tLS0tLS0tLSstLS0tLS0tLS0tKy0tLTgtLS0tLv%2FAABEIALkBEQMBIgACEQEDEQH%2FxAAcAAABBQEBAQAAAAAAAAAAAAADAQIEBQYABwj%2FxABCEAABAwIEAwUGAwYEBQUAAAABAAIRAyEEEjFBUWFxBSKBkfAGEzKhscFC0eEHFCNScvEVM5KyU2KCotIWQ1TC4v%2FEABgBAQEBAQEAAAAAAAAAAAAAAAABAgME%2F8QAIREBAQEBAQADAQACAwAAAAAAAAERAiEDEjFBMlEEExT%2F2gAMAwEAAhEDEQA%2FAPWXGyc10hc0WXU1xeg4Fc1KF0KppCklPcFyBsrpSErpRCkpqULkHSlBXIOJrtY0ucYAQGL1U9qdtU6RjN3hqBcjqNl577V%2B22JL8uHZUbR3fkMkfzMtOWxvHio%2BBrsdAzEvjVxknjDvPbbSyz8nX1mtcT7XGn7R9p3tyljT3nZTIkX34jeyztX27rF4BBZBObgRBPhYt%2BaM2oBZ%2Bki%2FQyAeG3mOQUPtXsxrySNXAO6kua23Oy48%2FwDIn9jp18N%2FlXXYnthUqukgFskARcXsSelvArUiuHwbTa3PcLHdkdnNoMEG8XPEm5jhcnnc9VasqyLEyOFhI2V%2F9E3MT%2FpsjRU4KKQs1ge1XNfkfSdcxmbJHWSArxuLbvI5FdpZXPCuw%2FBMY0jZSGuB0K53AqoZlzJrpboh1ZGiDTe92sQpVgtOvfRJiGNOpTzUyiCAq7F0c%2BiUQMXiQw5dRsm4XHd8Nbr8lIrdlmwtC6thY7rQAYUTBq%2BLBABtxUTPeB8KqsXQqucNco1UjDOgkGYIsuVlldZ%2BJTQJJHFGdObUKNSqtYZMQo2Kxvelt27pYsxYV6ILxOie6Ae7t9FCo4guu3RSWUXE5oNtQEZsSM3Irk%2BDwXIY1lN2yI0wUItRXN3XZkQpU3ZOCqOhIGIgShEBLFwYiwuyoB5V2VEISFANyyntBjv4gaCRxOZ0GIIBGmq0XatcspuIIBjU6Dw3XlLMZWqVKhrZHUy6R38rp2GW%2FAQZ3Kz8mzm2Nc%2F5JWJ9nXVnucS1rZmQxhJ0kEkcAFHf2G3DPaWuJbYEH4ZnVusQGmwVm3FkgaCObh9SdkTD0zU%2BKAwEG4Hei5see%2FReSd9Xx6LxJ6jOwAdM%2FD3gOMFsk%2BbpVU%2Bk5sNbmOQkZiZ%2BMjxsBPitiXGLAeIjhM%2BQsqLtrEijXp5oLandsBZwvJI8B4KXlZ1YLSwxcyXZs42kxYzoNze%2B%2FwBJGEYwUwSC2B3mnlx%2FNTsEwEAjnvfy30%2Baf2h2cXtJpkTs02mNQDxsVn6%2BeG7fQmPALXWgG14jw22566KyOLZVA8rwLco5rN4TF1mfG1waNTDfnAketFKxPaI95TyuIEQW2Op3PG2nNX4d%2B81Pk%2FPGhZSGxIRMjhwKjUqpF3WGoRKeKBMar3a8xXVDuFHe07FWBQnUgRwQQwziubigNAnVqUboTqZGoRDw%2FMdUNuFlxI1QDXyyBohUMU5rhBnks1UiphYlzj4Ksxj40EwrF1UvzE25JtLDOI0jgSoqpdTDxlIueKazBMEMNr%2FJW1Wk8mABKh1MC%2FPJAI6aK4anDsxsNhyNRgCNTsVWOzN7o1OglBNWo13Q6LPUNaTxK5Uv%2BJv%2FAJUqzlX7RtS4FPa8EdEIM4IlJw03K7IOwWStCZSCIqjg1KuJSojkgSpECpqcmv0Koyntpji2k5oAFjEnlq0TtxJHnC8lwdOqCXtaxoMy8gSeQ5eK9B9q7uJyg7SSY8Bq52vID54vFvb%2BK4GgGh6kbLl8veeOnx87dXOAa%2BqzI2ANC4xMb5Z1mNf0Bu6TrZOocY2A%2FKdvHVUvZ2OsOG0ZRAEcBz%2BSTE4dtUVH%2B9qUSXFofTNQZDmAaHsae%2B0xBgSJkLhzMdO%2FWvo1mgspm5cJIJmBB08pn5Ki9p8FTqVaJcCAKrC2I1l0B1tzHrSBh8S6nXrMr%2B8llMGjUzCpnaYe9raga3OxssguGbWSdn9otB9zV3a9tuRIB66jy5qXeb6syzWrFWnRpsP4c0OI1HPTUxrz5qa4AEwROnSRwWM7WxDaVCvUMucxoNKns6pm7k8RJB2sESnSre9b7zEV2Tc0XHDuqOcXAgvZSDgxjGyM0tLiWwGxeTbNpclxb43CPZU942Id8Qn5mdvzUDFYIlxIi99JAM3BG6s8TjCAwROZheTqBGXWL%2FiHkqlmKOaJtrlMW8f0lcr19evHSTYu8Jje4A4SRb1xUqm9mosqzC0M1p6HiFMLHM2Xv5uzXlsypEk9NkA1LprHyUSRstIcGPdER4qPVpVWz3hClYeudlIdWBEFKirNQR3vFVVXtAZ4bT8VcY3BZ4yuA4puF7PDblENw7TEmOiO%2FEEiSIaOCHie8RlDrbRqm4hxAywRyATF0fCYim42N95spb3Ni48iqhtFoEgXHFFwRnUEcOBUxS4hgnM1plQ6gc74m6bqxqVcupEIVfGW7o80xNV%2BQrkT3lTl5JFcTWxkrg6IshMlvEorXlRpIa%2FdFBUenwhEYtIKlTQuaiHyllMC5A%2FMmVCADOkXlIh12yDeOKqPNfa3Gl9UC4YNBoJ8d9fIrK4imHOgSTxNgOh9Ba72qoicw6TpF42PQR%2BgWPq1ACBMXFzvB4ary%2FNv2er4fwlKs5rw18hgLb3vDtNtpkK%2FwjYc%2BLtPeMcYGkcxKrcZVbUbmgZRECNNhbxKJha5DTEEhhLIgnQkC%2FETHqcy7Fsyl7Qa55GWARJcbgkRESLnYQT%2BHiEtPFyWh2gu02BnUfntoo9bEuD8kgSBqYzZZc4gbgZhy74N0w%2B%2FJDspA5gAG8%2BB9db1GZfVxRac7amuodrZxtOo2keMbq8pUYzE%2FwCY4AG0ibideGa%2FVUPZbnd0WIcdDo4NBnJxiGnfSFcU6wHfPecCS3ToPP7DgufVzxqOxtRrRlLhsBmi8aDkfXBVL6zJgETMRbXfQ6qN2linZ8riBIm%2FeabwflF1X3NQOtl5RA28NN1zs1158jcYAkRePVlYYjFEWMnoq7sdgIbDh0P5qwxFDWNSI10Xs%2BPzmPJ3%2BoVPE1CDDN99VLpNc4d6B01TcDhfdmSTcbqWWZjqusjFp9JrBqj%2B4Y4WsoxsOKaKzoE2SkSW4URBmeK73LhZplC%2Fe0f3ocOBUUB%2BcbWQXVtVLqUiRGYhV1R4YchdJVQF2P1E%2BBCbRxbpMCbI78LmExqomJoAcWnSRZVEaox2smeB2XEmQZkqfSwQs6ST1UxmFESAETFP7w%2FzLlY%2FuXMrkXGnYxOFIJspswpMaSITmi6EHpweqggCWEwncJ4KI6FxakJS5kDYXPZIIOhSyklUeeftS7QbSpNpi3eBd5WbzOp6cZXmWHx%2BcyLE6cdOM2Xrvtn7NsrTWeC6xEcZLQ1jT%2BEHcxJ56HxH2jJoYipTEbk8ASLDpM%2BQWO%2BPs1x39ataONLSWbFpkz9OSn9lVmsqZqhzF2WxAAbMCTNnkTsQQBoCshj8e5ozMO8TyHDyTcPjXupzMudPLlEi%2FD5LnzxntdO%2B98j0vtH2aZjR7t5dlLswLSCWkiJBcJEgARfaFUVf2c4inVbh2VgaTmveHGM7WsdTa5vwwXfxRDtoPAKg9lO1q9PECj%2B8Gi1zXDPFMhhyOdTJzNMNLiwRzsQp7cD2mXCu3EOc7KYe14fMkEtb%2BG%2FdOWOHC3Tn4%2BudmufXU69xtexvY6ngnvDX1Hh0CaoYXtGbM5rTOUBxaCbXhP7bx5pvd3ZAEOEtzTEzJNnAAWyxr1Wb9ovabHNFOi%2Bsw1TRY55psLT3iSe9mIMNaLgD8WwCzeJxj6rnSSJAMSTBFoB6DXdefr47v26u67cdSzJPxZY3tDvBwcSCHawSbgb8hdPqdoBrc0gTsbf269FRUgRc6Ty4bfNRsXi%2FeWkWtcFJ8e1rrvJjS9k%2B0WWoO9br%2BV%2FGF6p2Djm1m91wPHW3r7LwGhhzIBOvAR817D7AYV7GaQI4t3i8D6m69UkkeW2761uIpcElKlb4lFdUd3i7ZcytvKlqpLqfNMc0whtxM80fL%2FZZUGqwwmhxaNVM95A0QS9ptr02QA%2FenHdIysQZgSnlu1h0Ti8ReFpGdxPtG6lVcDTdaNLgjcwr3Bdq0sQ3Mwg8tx1lRcX2fSqmSDI3BgpaHZNFjg5oIPI69VfE9WLXBEpVAN0CGrixovKipudvJcoPd%2FmC5BYMrX3U1koLKYJvCkjipiiMclcla4ck4xyWkOAsuauplKQiOK4pQkJQMBXFcClAQDrU8zS08F86ftB7NLcbXm8OBJPAtaQAALDvEAcGr6NAMrxX9p2Ey43ET%2BNlN7f6SyD%2FANzSrqWPMnsLddCBYpuBr5CRsdPNJi6hJPr%2ByjC9%2Bfo%2BuSv18T7ZfHqfYnY%2BHx2FByxVDcju85uYt%2Fyw8AgOgBvy5K1wnsrXzFvvWe7ye7DcjSQ0CGEOIkm5PCSTEuJXk%2FZPbdbDOa6m42vEmDMa8rfVXeE%2FaPimvLiZs4ACwuSR5TCznf8AK1vN%2FY3Xavs9huz6FSo5zn1nscylmcIl2uXQTqZ1glYmt2eT%2FEZ3m%2FijURchw5SNOPgq%2FtP2pqV4LiS2ZLDpOuh5zbabKR%2Fj2ZhEAWbdti2NDbUeOhI68uuer%2BuvHUgWIqlrSBqDt5%2BKryc2og8dJ8QpFbFFx7%2FieO%2F6rqWTiCN%2BHjGn6LcmRm3aLhKpETccDB8l6%2F7D1WupDK5xaRZpBMEW%2BLQleT9i0KLq4bUMNIOptMiL3Ea%2BtfZ%2FZTsxtClNJrTmJMh35z9VuRz3atKmAGxIO6jO7Py3JzK0o1HfjEH1ug3mA4dD%2BaiolKi0DT80T3gFhrzRH0XTrrrCa%2BlmIi3mmKjNY9wglFpUXASj4kupnNIyxFtUmEqMqOJDjPArOGgOtdVtVznEgGAeGpV%2FWbAMxCiNplw0EbEC6auIeHohl580d2KGkSeSfUweYRPgRqo7sG8CJAaNYsn9RIpPBEmAeCK6kDoYUShTvA06WRKmKaLSSeAC1iHe6PH5LkH9%2FP8AI75LlMNXwAbf0VzMWDaE8t9FJlbwRRQQnMppobwT1UEaEQGUDMjMQEDU1zUgTlUIEsJrglCiuIXlf7YMKPfUKmuam5jv%2BlwLP958l6oQvNv2qn%2BIwmIFKWzxzPzfLKqjxfEYYuJMf3Kg1qOUxdbipgdDGswPr8580Fvs5Mk%2FFcg8eNvt0UvefqfXWPZQkSeEjysoT2LTdp9muaDl6EdNvmfJZ4CSLG%2Fr10WuOt9OpnhGi3gnZiPQ9ShtF%2FXFJVWmfxOoO1E6eP1TKreaiir65JGkuP5LOLvi%2FwDZSi91cO9254BuAJnkAQQSY032vC%2BiexsI1tFmXSJ8%2FAfRYb9j3ZQdh3uqtaQ4gjcu2IfPDKP9R5L0twGiWrDA3dcaLTqErhuh5ZvKimtwwBsf7oT8MSNO91%2BilsbHBAq4jJJJ8AoqK2lHxDzM%2BSJlaNo4I474Do1TRhyL68kwLnB3TKj%2BASOZN8h57IJi%2BoUsXQ69Y2j4vl5oRL2iSQRuN0Wo0ETm%2BsqC%2BkWkl0luotP0QK7GcAdeKBRlpJLSSbkzYBJh2tEkMIkefmuDS0Ey4DcHRIlH%2FemcR5pFA%2FfGcvmkVRuwE19O36JtSplBM2GsXsn0sQ1wBDgZCKbTtZGI5JgbISZiOqB5aYTmC1kgcm%2B8DbxZUHCeChNfMFPzIhXLgkekCB5XnH7XKZIphuuV8iBcEtmZ%2FpXopKyP7RuzzUoNrNbJoklwGvu3DvHnlLWnpmQeedmAGlTn%2BVo5ggX%2BYV7Uws0rRInbx18fmqXAPEN27xB4iTM%2BuC1HZZlrgTe8891x79jpzMZjtTsaoaLmhs1DOUCJMkhsczbzVfhv2aPdh2uuH5BIkQariSGCAYYxkAnd7oEZSvRW0rB0AuaQQDvBBHzC2GEyuaCB3XXGkjNc%2BMkq%2FDfKny%2Fr5loezpzlsy0EiY%2BIg6gcwfmtBjvZKm2g9wMloLoGpP3tZHwTRni8RY9DF77%2FAGWiNMOY4EDQxrwWO%2Fk6lb54mPJn9kuJgCT6j8vFWPYnYT2VmGpTdkkXAJAvcG2nyWu7HwGZxMTBH3v9Fs8Phw2nBgncR9Vu%2FJfxj6RZeylFrJa0QC2fmNfNaFU3s%2F8AG7k37iFeOsunN8Zv6jucZiCfAQuyc%2FJHzodR260iPAdIk2USpgeZlT3O6oTRuCb8fyUwMo0cojMeKICefJMLT1%2BqDRxHGeSA%2BeCeNpTHuvI%2Bya%2FECN%2FK%2Fih%2B%2BYTz6fmg6tiTsyeGgUJ9fETak2P6gFLq0w8QYcOG2tkPLHdJkbAlXBHpMql2dw7sWAO%2B9t1JfQa4QTHP9E4EZYbA4QPBR3MAJcBDjAJi5jSUwD%2Fw5v8AxB5BchfvTP5z68FyYjQPdrd19RKd73ZkCBFx%2BSGJk78j9imsNzYzz%2Biy0O2u4ajqpFKvI0QGttfT6FLTGU7IJYulATabwR9VHrdo0mm7x858lRNASAJlF0gEaHREciGylmyFUeON0Cm2sLEsjY3mOiCVmtaT8l1WoGtc52gBJngBJTSVVe02PFPDukwXQ0c7yfkD5hB5NhqIZUqU%2FLhLZ7vKxPktH2RiQHTxsb%2FiABB8W%2FRZxz4eXa94nmrjBNBcADqJHgSftPiVyro3GHw8j1xVp2aCGFhOhMdDf65lSdh4xrmtcNYE30MaHgrfE120m1KhPdFMuPRgJ%2B5Wfj8q9%2Fjx7BsaSyZED1dXVSsAIgR4n%2FSIKqsMQabST%2BEX56ET1CtvYvAmvig4kllEh7hqM0%2Fwxrxv%2FwBB4rFn26xvc5Sew8IWuqNIhzHwR0kEGOBV5XENuNOEbfT9UDE0QMRVjQvzW5gEjXiSlxzwRa3P9fJOvOknsW3szSs89Pv%2BQVy3dU3s7XAbkuXHvG1ogD4tONlauxLZguAJ0EiT0Er08fjjf11Q739dEnvhy8ikqvDQS4wNybKqf29QBBFTMJIMBzo62WkWhjb5IdSmNb8bKqxftRhqZA95Jd8OUSDynQHkiUfaPDPOVtZma1iYudhsXcgVBPncTHA%2Fquc7kq3tftplEAlj35jfI2bbngdrSqXFe1%2BGAcSHADckNE7DUmT0P1QaV4AgzA3B%2FNCBcTBgDY8fBUGE9oaD6YqAPzGxpDvVB4Nnu%2F8ANYc0FntAZg4dzLmJqU5Gh74zS0xOsINI4XgC4GsDw1Uas0DX5xqs32t7Zsw93Ms74Bmhx1l2Q%2Fh5mJWar%2B2VSu1zQG0834mPIcL%2FAA38bi9wrKPRnP0g31gz08Ao9XEEf2meFl572RjKwzuGJzBwLRn%2BJroHehzpkASLRffZKuMrMzTUe5xMkzDG5WjLpEPJvoBAEi12mN9ldw%2BTfySrzz%2F1O7%2Fit%2F1s%2FwDJcmmPbG0dwOunzCUA6FvrkU393aIJmY3e4x0k%2FRKHcxO%2FNZUtxzG%2BgI81Axfv3PYWPYwNMkOaSHjgX%2FhnkCrCo6N7HxI8ZTWkRpJGoIFx9EDm4xptJkfy3vvDtCjVKjBDiQLxP6xZQ61SO80CNDy%2FREDgbgx0N9%2BKmmJD8W1ph1RoJ0EifrzRWYkG03G26qKnZVB7s5ZTJOpMjNaO9Bh1o14KXUgAEBvUAeF1dMHxFcNjU9GudHWNEShXa8S0z63GygO7w1jfmkZTfoapLTraHeDmkR5JosyF5v8AtDx%2BesKMwGAdc183SxHktyaBBgVHxHJ3zcCvLvbzAvo4xznXZWg03f0tb7wGIAIIno4FKRU%2B8nXmrns6r3QRq2IIFpaZA5SLeBWVpVDeXTBgDruV6X7P9hE4EyP4lQe8aNpH%2BX5iR0eueNaTD1Q0tqNENfOa%2B4%2BGB1HyCte3qL8TgatOkYflaQB%2BINIc5viARHTisx2a%2FNTLZmQTP1haTsLGFpvsY4TwXK7Lrp%2BzGNwWGlga5sgjSNOBkct%2Bq0Psv2nQwuHY0jvVC5zsvUhs3k90D58UV%2BC93iHNAEZgW%2F0HvNAHIGPAqs7XDadU07ABrS0b5TeOepHgunPltY69kWzawc5z4jM6Y3E3j5oGNqm0b2HEqt7K7Wa7NmImbg%2BO6fiMdTfHe02B335%2F3XHq7ddZPMTO0MPicjRTc4smzWwII1njPNRf8GcHB9WoGutE62F7281qOx6QdTaS4gu7wE7HS286%2BKmOwoIgtnjmEz5iF6%2Bb489%2FWBxeKqDve7c%2BZE9%2BqY84jnbVV%2BL7OxVVndYGU9YkAkk7skk%2FK69Oo4NrZhjW9Nx0ATHYVoBhuWdxt0vZVHmlDs%2Bs4BrqVQgSWgFwE7kwCTMayi4T2dxcOD6gYH2zd0uA63jw81v24VgiJNvxHMfMyUtNrCSdxbQjThxHyUGawHs5SY0SalUlsOcS8EkX%2BFpyxOymHswgAspiRMBxt4z%2BSumUgJy%2Fp87DySAmLwOX625IrOnsN5ObLSpuiCWfFfWDlHDQ8uCgYr2NbVOao8E63BmdyCCINh81pq9RtpceUPI4a5SM3RCdjmQZzcJAgjxUGVxvsZTIYx9VzhmzGZlzu8D3ic3wuDYnQdFCqewVIXFSoAbkDKJvoS7N5ehramLbJyNquncaATG%2Fd4mTfXwIQcpPu3m1oJ4dI9eV0xjH9g0msAa0xJNi0xsSDIg32Cqn9gVGOEYlzRM3ZTcdNMwEk24WXoTuzs4%2BCw0MiRbWcwI4IWI7DJs2m0QAJJZmm1hmDuAN%2BAVSsL%2B51%2F8A5r%2F%2B%2FwD8Uq3H%2BCVOLP8At%2F8AFcg2Dza8efqECozgQPK%2F2Pin7i%2FlafNDz85Hz8FF1zKsWJJGkcefXomvJ0B%2FpPEcCfWi58RqQPUTKa0mNyNwRbqCND0Q0%2BnvIsdQYsnvpEDWBxbB80AzI16nX%2F8AV7KRTfA5bj62OiYaVhdxB4QfsnPcYvHh69Smh45iOnlOvnCaKp4COR%2BcdUw05jTO0X620gbqQxp149FFfUIFom3xEtseYBk8AlxD2kAZiNPh1teLqyCY2M0KJ2t2XRxNM06zA9pIMSQQRoWkXaYJEjYkaEpwxI3JFjJgHa8xuniuP5h42RGfwf7PcAyB7p74IcM9R5gjT4SARyK0QZGndiAIAsBoAkbiW8RPVI6qJmdVcGGxWG91iazG2h7XttYCoRmiLRlc4eAUqg%2BHmx0gzPxSbdbqy7WA98HggF4a3YZi3MYbxOVw6BUvauMFAB9VzGNJ%2FE7LMyYGaC46mACdLXC83fN3x156n9Wvaj2jJUz3HcdcSLzTLgdLlw8kfGdhYfHNY%2BqHB7RGZjsro1ynUEXJEgxJjUzQYKvSr5src7Ce8IvdoLgLyDlIMEAxcAqzwealWpBrv4d85izm5DAMCx0IP%2FLwK18cv5U7%2FwBxXVvYF7XTSxIy7CpTOYci5ph3XKFNwHsUGuDq9b3gscjG%2B7Zb%2BY5szh0hahuIadz5QuNduknwXT6c%2FwCmft0StSBGk8IAhD0sAZ9c0QVQf19aJrsRA4fSecK%2BJhhFTYH%2FAFAf3TalKqNGZp%2F5mgdCSfoE9uLk6bx9N%2BvBL7w37w8BH9%2BPimphrsI%2FLJgGJgG8xpMwg4fDvLQXjIf5Ya6PGSPJSHVDv8z9P1%2Fujn6CJnhFrTNyLdN4RQjgmm7ocbwcrCQNYBISDCN3cTtq0c4sJR%2FebcftrokLp0m359EDPcM0IHmmHDMt3Gf6QY4wUYppagaT1%2BYA%2FRMczczPWP7J9uMcZ%2FXqlDB69XQDeBz%2BcTsfP6JOHl16fJPyjl6%2Bi4EbWAJ3BniECTzPk78ly6B%2FP8x%2BS5AYakDUc9BzHh8kpYfy%2FPQc7KfX%2BE9T9Wp%2F4h%2FV9ioK51G%2Bt%2BvTw3CaaObSInk7Y%2BWvh4qxHxef1cm%2FiH9Q%2BoSCE6jyPSbfMhBxDqdIZqjmUwSAC6GiTYd4mBOl9YU86N%2Fpb%2FuUlnxeuKorvdg2sbC%2FgbgiIK5%2BE339c1No%2Fcp51Hh9UFecObJrsMdfPUD5mNlN29cE6n%2BHp%2BammIJwXL5R%2BpXHCHx6kD78VOqfDU%2FpP%2B0p7t%2Bv3VFT%2B6O0i%2BsTN945T6CFUwzx%2FwC28jz%2B6uW%2FCfWyeNPD80lRgcUzGfvVN%2FuXOpMD2tDab%2FxAOJcfxSWAaxcaLN43snH1XgYnCurtzOFmPblEnI5mpkgN6W1kr2Wjr4J9TQ%2BH3VnqPKuy8LicIfcDC4ipTBFSi4tJNMa%2B5fA%2BEEkCDo8i2q0fZuBxWYh0im17w3My76ZAdTk6gtlzTxDW73OvH4lEqf5zP6XfViixGGGO%2FwBvr61Tjg4n7jXnHBSu0v8AKqf0lLhfhb0b%2FtaioLcO0d1sDfKABHgPXSU%2F3Ann1Pz8ypVX4R1P0KV%2Bh9cVBDbQ0vPGxM%2BZkHXjqmPojQagKYfy%2FwBxQnat6%2F8A1KAVRhGgm97xbrGq4ATE%2BW%2FKI4kcD95Y%2B5%2BrUOp8PrgUEem8GcpBgwYOkag8CueD69CFLqfkgM1HgqAtnSxI25aaetFzmu%2Fv66%2BSk1tPEfdRqHxHqPoEAKuEbmDyGZv5oAd4O1jS0p9hb6af2S4jU%2BKg4r8H9X2WbVTPejSxi%2FDx15%2FTolbxFvXFV%2BP%2FAMyl%2FV9grOjv0P0VQKPXoLlYrkH%2F2Q%3D%3D&descript=lion");
Request request = new Request.Builder()
  .url("http://164.68.101.149:12345/yesipov/addImage")
  .post(body)
  .addHeader("Content-Type", "application/x-www-form-urlencoded")
  .addHeader("User-Agent", "PostmanRuntime/7.18.0")
  .addHeader("Accept", "*/*")
  .addHeader("Cache-Control", "no-cache")
  .addHeader("Postman-Token", "0dc82212-836d-497d-8439-4f9a8aa8a552,5ddb8c7e-d9c2-4948-85f1-3c5e0b2604cc")
  .addHeader("Host", "164.68.101.149:12345")
  .addHeader("Accept-Encoding", "gzip, deflate")
  .addHeader("Content-Length", "11372")
  .addHeader("Connection", "keep-alive")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();
```
##### Response
`Image posted ID = 26`

### 2) GET запрос(вернуть список всех изображений id, descript)
#### Пример
##### Request(Java OK HTTP)

```java
OkHttpClient client = new OkHttpClient();

Request request = new Request.Builder()
  .url("http://164.68.101.149:12345/yesipov/getImages")
  .get()
  .addHeader("Content-Type", "application/json")
  .addHeader("User-Agent", "PostmanRuntime/7.18.0")
  .addHeader("Accept", "*/*")
  .addHeader("Cache-Control", "no-cache")
  .addHeader("Postman-Token", "a469d9b0-e35f-435c-8170-0fcc3cc82ec5,aa90af0f-d48e-4b58-ad5f-821635ab2192")
  .addHeader("Host", "164.68.101.149:12345")
  .addHeader("Accept-Encoding", "gzip, deflate")
  .addHeader("Connection", "keep-alive")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();
```

##### Response
`[
   {
     "id": 26,
     "descript": "lion"
   }
 ]`
 
 ### 3) GET запрос(вернуть изображение по id)
 #### Пример 
 ##### Request(Java OK HTTP)
 
 ```java
OkHttpClient client = new OkHttpClient();

Request request = new Request.Builder()
  .url("http://164.68.101.149:12345/yesipov/26")
  .get()
  .addHeader("Content-Type", "image/png")
  .addHeader("User-Agent", "PostmanRuntime/7.18.0")
  .addHeader("Accept", "*/*")
  .addHeader("Cache-Control", "no-cache")
  .addHeader("Postman-Token", "fb18011d-8de9-4f87-95df-0159c0eafc37,2a16a8a5-fabb-4f6c-bf43-6f311eea265d")
  .addHeader("Host", "164.68.101.149:12345")
  .addHeader("Accept-Encoding", "gzip, deflate")
  .addHeader("Connection", "keep-alive")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();
```

##### Response
![lion](https://github.com/RodionYesipov/testImages/blob/master/index.jpg)