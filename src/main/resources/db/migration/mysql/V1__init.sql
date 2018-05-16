CREATE TABLE `pessoas` (
  `idPessoa` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `dataNascimento` datetime NOT NULL,
  `dataAtualizacao` datetime NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `cpf` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `contas` (
  `idConta` bigint(20) NOT NULL,
  `dataAtualizacao` datetime NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `saldo` float DEFAULT NULL,
  `limiteSaqueDiario` float DEFAULT NULL,
  `tipoConta` varchar(255) NOT NULL,
  `flagAtivo` varchar(255) NOT NULL,
  `id_Pessoa` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transacoes` (
  `idTransacoes` bigint(20) NOT NULL,
  `dataTransacao` datetime NOT NULL,
  `dataAtualizacao` datetime NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `valor` float DEFAULT NULL,
  `id_Conta` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `pessoas`
  ADD PRIMARY KEY (`idPessoa`);


ALTER TABLE `contas`
  ADD PRIMARY KEY (`idConta`),
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54j` (`id_Pessoa`);


ALTER TABLE `transacoes`
  ADD PRIMARY KEY (`idTransacoes``),
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`id_Conta`);


ALTER TABLE `pessoas`
  MODIFY `idPessoa` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `contas`
  MODIFY `idConta` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `transacoes``
  MODIFY `idTransacoes`` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `contas`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54j` FOREIGN KEY (`id_Pessoa`) REFERENCES `pessoas` (`idPessoa`);


ALTER TABLE `transacoes``
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`id_Conta``) REFERENCES `contas` (`idConta`);