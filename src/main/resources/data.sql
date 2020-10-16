/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  everton
 * Created: 16 de out de 2020
 */

INSERT INTO usuarios (usuario_nm, usuario_ap, cpf_num, email, senha) VALUES
('EVERTON DA SILVA LEANDRO', 'EVERTON', '06247473362', 'everton.leandro1994@gmail.com', '123456789')
ON CONFLICT (cpf_num) DO NOTHING;