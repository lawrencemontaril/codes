import pygame
pygame.init()

screen = pygame.display.set_mode((720, 480))

class Player(pygame.sprite.Sprite):

    def __init__(self):
        super(Player, self).__init__()
        self.rect = pygame.Rect((328, 208), (32, 32))
        self.image = pygame.Surface((32, 32))
        self.image.fill((194, 198, 207))
        self.velocity = [0, 0]  # It's current velocity.
        self.speed = 1  # The speed the player will move.
        self.dx = []  # Keeps track of the horizontal movement.
        self.dy = []  # Keeps track of the vertical movement.

    def update(self):
        try:
            if self.rect.x >= 0:
                self.rect.x += self.dx[0]  # Index error if the list is empty.
            if self.rect.x <= 0:
                self.rect.x = 0
            if self.rect.x >= 688:
                self.rect.x = 688
        except IndexError:
            self.rect.x += 0
        try:
            if self.rect.y >= 0:
                self.rect.y += self.dy[0]  # Index error if the list is empty.
            if self.rect.y <= 0:
                self.rect.y = 0
            if self.rect.y >= 448:
                self.rect.y = 448
        except IndexError:
            self.rect.y += 0

player = Player()
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            raise SystemExit
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_a:
                player.dx.insert(0, -player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)
            elif event.key == pygame.K_d:
                player.dx.insert(0, player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)
            elif event.key == pygame.K_w:
                player.dy.insert(0, -player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)
            elif event.key == pygame.K_s:
                player.dy.insert(0, player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)
        elif event.type == pygame.KEYUP:
            if event.key == pygame.K_a:
                player.dx.remove(-player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)
            elif event.key == pygame.K_d:
                player.dx.remove(player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)
            elif event.key == pygame.K_w:
                player.dy.remove(-player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)
            elif event.key == pygame.K_s:
                player.dy.remove(player.speed)
                print("X: ", player.rect.x, "Y: ", player.rect.y)

    player.update()
    screen.fill((29, 29, 29))
    screen.blit(player.image, player.rect)
    pygame.display.update()

    # print player.dx, player.dy  # Uncomment to see what's happening in action!
