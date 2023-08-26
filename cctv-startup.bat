:: Завершить все процессы vlc.exe:
taskkill /f /im "vlc.exe"

start vlc -vvv -Idummy "rtsp://admin:admin@192.168.31.27:554" --sout #transcode{vcodec=MJPG,venc=ffmpeg{strict=1},fps=30,width=1920,height=1080}:standard{access=http{mime=multipart/x-mixed-replace;boundary=--7b3cc56e5f51db803f790dad720ed50a},mux=mpjpeg,dst=:9911/}
:: "rtsp://10.0.79.13:554/user=admin&password=&channel=1&stream=0.sdp?" - RTSP ссылка камеры. У каждого производителя она разная
:: fps=25 - количество кадров в секунду; width=1920 - ширина кадра; height=1080 - высота кадра; dst=:9911 - порт

:: |второй RTSP| start vlc -vvv -Idummy "rtsp://10.0.79.13:554/user=admin&password=&channel=1&stream=0.sdp?" --sout #transcode{vcodec=MJPG,venc=ffmpeg{strict=1},fps=25,width=1920,height=1080}:standard{access=http{mime=multipart/x-mixed-replace;boundary=--7b3cc56e5f51db803f790dad720ed50a},mux=mpjpeg,dst=:9911/}

:: Поместите этот bat файл в корневую папку с vlc плеером.
:: Пример пути к vlc плееру `"C:\Program Files\VLC\vlc.exe"`.

:: rtsp://10.0.79.13:554/user=admin&password=&channel=1&stream=0.sdp? пример RTSP ссылки для XMEye камер

:: Access the http-streams like that:
:: Первая камера: http://127.0.0.1:9911 
:: Вторая камера: http://127.0.0.1:9912 
:: `127.0.0.1` это адрес сервера (или ПК), на котором запущен VLC.
:: Порты вашего сервера, которые вы используете для потоковой передачи (9911 и 9912 из текущего примера), должны быть открыты для внешнего доступа.