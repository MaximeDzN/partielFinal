resource "null_resource" "ansible-provision" {
  depends_on = [aws_instance.swarm-master, aws_instance.aws-swarm-members]

  provisioner "local-exec" {
    command = "echo \"[all:vars]\" > ../Ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"${format("ansible_ssh_private_key_file=%s", var.private_key_path)}\" >>  ../Ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"[swarm-master]\" > ../Ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"${format("%s ansible_ssh_user=%s", aws_instance.swarm-master.0.public_ip, var.ssh_user)}\" >>  ../Ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"[swarm-nodes]\" >>  ../Ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"${join("\n",formatlist("%s ansible_ssh_user=%s", aws_instance.aws-swarm-members.*.public_ip, var.ssh_user))}\" >>  ../Ansible/swarm-inventory"
  }
  provisioner "local-exec" {
    command = "ANSIBLE_HOST_KEY_CHECKING=false ansible-playbook -u ubuntu -b -i ../Ansible/swarm-inventory --private-key ${var.private_key_path} ../Ansible/swarm.yml"
  }
  provisioner "remote-exec" {
    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key =  file(var.private_key_path)
      host        = aws_instance.swarm-master.0.public_ip
    }

    inline = [
      "sudo apt update -y",
      "docker run -it -d -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock dockersamples/visualizer",
      "git clone https://github.com/MaximeDzN/partielFinal",
      "cd partielFinal/",
      "sudo docker stack deploy -c docker-compose-swarn.yml my_app",
    ]
  }
}
#resource "null_resource" "ansible-master" {
#
#  depends_on = [aws_instance.swarm-master]
#
#  provisioner "remote-exec" {
#    connection {
#      type        = "ssh"
#      user        = "ubuntu"
#      private_key = file("ssh_key.pem")
#      host        = aws_instance.swarm-master.0.public_ip
#    }
#
#    inline = [
#      "sudo apt update -y",
#      "docker run -it -d -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock dockersamples/visualizer",
#      "git clone https://github.com/Flav1-ann/dockcomp.git",
#      "cd /dockcomp",
#      "sudo docker stack deploy -c docker-compose-swarn.yml my_app",
#    ]
#  }
#}
 