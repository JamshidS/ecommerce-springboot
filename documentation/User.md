## Tüm Kullanıcıları Listeleme

### Endpoint
`GET /users`

### Açıklama
Bu endpoint, sistemde kayıtlı olan tüm kullanıcıları listeler.

### Başarılı Yanıt
`200 OK` status code ile tüm kullanıcıların listesi.

### Hata Durumları
- `404 Not Found`: Kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcı Bilgilerini Getirme

### Endpoint
`GET /users/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının bilgilerini getirir.

### Path Parametresi
- `uuid` (String, zorunlu): Getirilmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kullanıcının bilgileri.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcı Oluşturma

### Endpoint
`POST /users`

### Açıklama
Bu endpoint, yeni bir kullanıcı oluşturur.

### İstek Body Parametreleri
- `userName` (String, zorunlu): Kullanıcı adı.
- `email` (String, zorunlu): Kullanıcı e-posta adresi.
- `password` (String, zorunlu): Kullanıcı şifresi.
- `telephone` (String, isteğe bağlı): Kullanıcı telefon numarası.
- `address` (String, isteğe bağlı): Kullanıcı adresi.
- `userRole` (UserRole, isteğe bağlı): Kullanıcı rolü.

### Başarılı Yanıt
`201 Created` status code ile kullanıcı başarıyla oluşturulduğuna dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcı Güncelleme

### Endpoint
`PUT /users/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının bilgilerini günceller.

### Path Parametresi
- `uuid` (String, zorunlu): Güncellenmek istenen kullanıcının UUID'si.

### İstek Body Parametreleri
- Yukarıdaki "Kullanıcı Oluşturma" endpoint'inde belirtilen parametrelerin aynıları.

### Başarılı Yanıt
`200 OK` status code ile kullanıcı başarıyla güncellendiğine dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcı Silme

### Endpoint
`DELETE /users/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcıyı siler.

### Path Parametresi
- `uuid` (String, zorunlu): Silinmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile kullanıcı başarıyla silindiğine dair mesaj.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının İstek Listesini Getirme

### Endpoint
`GET /users/wishlist/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının istek listesini getirir.

### Path Parametresi
- `uuid` (String, zorunlu): İstek listesi getirilmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kullanıcının istek listesi.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının İstek Listesini Güncelleme

### Endpoint
`PUT /users/wishlist/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının istek listesini günceller.

### Path Parametresi
- `uuid` (String, zorunlu): İstek listesi güncellenmek istenen kullanıcının UUID'si.

### İstek Body Parametreleri
- `productUuids` (List<String>, zorunlu): Güncellenmiş istek listesi ürün UUID'lerinin listesi.

### Başarılı Yanıt
`200 OK` status code ile kullanıcının istek listesi başarıyla güncellendiğine dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının Ürünlerini Getirme

### Endpoint
`GET /users/products/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının sahip olduğu ürünleri getirir.

### Path Parametresi
- `uuid` (String, zorunlu): Ürünlerin getirilmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kullanıcının ürünleri.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının Siparişlerini Getirme

### Endpoint
`GET /users/orders/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının siparişlerini getirir.

### Path Parametresi
- `uuid` (String, zorunlu): Siparişlerin getirilmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kullanıcının siparişleri.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının Sepet Detaylarını Getirme

### Endpoint
`GET /users/cart/details/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının sepet detaylarını getirir.

### Path Parametresi
- `uuid` (String, zorunlu): Sepet detaylarının getirilmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kullanıcının sepet detayları.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının Sepetini Getirme

### Endpoint
`GET /users/cart/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının sepetini getirir.

### Path Parametresi
- `uuid` (String, zorunlu): Sepetin getirilmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kullanıcının sepeti.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının Sepetini Silme

### Endpoint
`DELETE /users/cart/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının sepetini siler.

### Path Parametresi
- `uuid` (String, zorunlu): Sepetin silinmek istenen kullanıcının UUID'si.

### Başarılı Yanıt
`200 OK` status code ile kullanıcının sepeti başarıyla silindiğine dair mesaj.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcının Sepetini Güncelleme

### Endpoint
`PUT /users/cart/{uuid}`

### Açıklama
Bu endpoint, belirtilen bir kullanıcının sepetini günceller.

### Path Parametresi
- `uuid` (String, zorunlu): Sepetin güncellenmek istenen kullanıcının UUID'si.

### İstek Body Parametreleri
- `updatedCartDto` (CartDto, zorunlu): Güncellenmiş sepet bilgileri.

### Başarılı Yanıt
`200 OK` status code ile kullanıcının sepeti başarıyla güncellendiğine dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Belirtilen UUID'ye sahip kullanıcı bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.
