#!/usr/bin/env bash

psql -U postgres -c "\copy pfm_mcc FROM mcc.csv delimiter ';' csv header"
psql -U postgres -c "\copy pfm_our_mcc FROM our_mcc.csv delimiter ';' csv header"
psql -U postgres -c "\copy pfm_transaction_data FROM tr.csv delimiter ';' csv header"