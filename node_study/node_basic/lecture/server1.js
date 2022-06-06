const http = require('http');

http.createServer((req, res) => {
    res.writeHead(200, {'Content-Type': 'text-html; charset=utf-8'})
    res.write('<h1>Hello server</h1>')
    res.write('<h1>Hello server2</h1>')
    res.end('<h1>Hello server3</h1>')
})
.listen(4000, () => {
    console.log('4000 서버 로딩')
})