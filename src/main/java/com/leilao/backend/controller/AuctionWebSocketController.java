package com.leilao.backend.controller;

public class AuctionWebSocketController {
    


    @MessageMapping("/bid/auctionId") // mensagem a ser enviada
    @SendTo("/topic/auction/{auctoinId}") //para quem devem ser encaminhadas as mensagens --> todos inscritos no topico
    //para cada leilao, salva o registro

}
