param(
    [Parameter(Position=0)]
    [string]$Command = "STATUS",
    [string]$HostIP = "127.0.0.1",
    [int]$Port = 8080
)

try {
    Write-Host "Connecting to TCP Server at ${HostIP}:${Port}..." -ForegroundColor Cyan
    $client = New-Object System.Net.Sockets.TcpClient
    $client.Connect($HostIP, $Port)
    $stream = $client.GetStream()
    $writer = New-Object System.IO.StreamWriter($stream)
    $reader = New-Object System.IO.StreamReader($stream)
    $writer.AutoFlush = $true

    Write-Host "Sending command: '$Command'" -ForegroundColor Yellow
    $writer.WriteLine($Command)

    $response = $reader.ReadLine()
    Write-Host "Server response: $response" -ForegroundColor Green

    $client.Close()
} catch {
    Write-Host "Connection error: $_" -ForegroundColor Red
}
