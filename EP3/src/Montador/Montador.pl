#!/usr/bin/perl

=comment
 \__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__

Nomes:
    Danilo Novais (N.USP: 7990759)
    Fernando Câmara Bizzotto (N.USP: 7991211)
    Ricardo Mikio Morita (N.USP: 5412562)

Prof:
    Marcos Dimas Gubitoso

Arquivo:
    montador.pl

Materia:
    LabProg II (MAC 0242)
    Exercício-Programa 2

 \__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__
=cut

use strict;

open (FILEOUT, ">../Programa.java") || die ("ERRO: CRIACAO DE ARQUIVO -> Programa.java\n");

my $line;
my $i = 0;

$line = "public class Programa {\n";
$line .= "	public Instrucao[] getPrograma(Arena mapa) {\n";
$line .= "		Instrucao[] programa = new Instrucao[512];\n\n";
$line .= "		Numero num;\n";
$line .= "		Texto txt;\n\n";

print FILEOUT $line;

#Recebe o arquivo em texto com as instruções em Assembly
while(<>)
{
	if (($_ !~ m/^(\s*[#]|\s*$)/x) && ($_ =~ m/(\s*(\w*):)?\s*([\w]{1,4})?\s*(\w*)/x))
    {
		#$2: label
		#$3: instrucao
		#$4: parâmetro

		$line = "		programa[$i] = new Instrucao();\n";
		print FILEOUT $line;

		if (defined $2)
		{
			$line = "		programa[$i].setLabel(\"" . $2 . "\");\n";
			print FILEOUT $line;
		}

		$line = "		programa[$i].setInstrucao(\"" . $3 . "\");\n";
		print FILEOUT $line;
		
		#instruções com parâmetros:
	  	if (($3 eq "PUSH") || ($3 eq "STO") || ($3 eq "RCL"))
		{
			$line = "		num = new Numero(" . $4 . ");\n";
			print FILEOUT $line;

			$line = "		programa[$i].setOperando(num);\n";
			print FILEOUT $line;
		}
		elsif (($3 eq "JMP") || ($3 eq "JIT") || ($3 eq "JIF"))
		{
			$line = "		txt = new Texto(\"" . $4 . "\");\n";
			print FILEOUT $line;

			$line = "		programa[$i].setOperando(txt);\n";
			print FILEOUT $line;
		}

		print FILEOUT "\n";
		$i += 1;
    }
}

$line = "		return programa;\n";
$line .= "	}\n";
$line .= "}\n";
print FILEOUT $line;
close (FILEOUT);


